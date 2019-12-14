package net.furkanakdemir.noticeboard

import android.content.Context
import net.furkanakdemir.noticeboard.Position.TOP
import net.furkanakdemir.noticeboard.config.ConfigRepository
import net.furkanakdemir.noticeboard.config.ConfigRepository.Companion.KEY_EMPTY_ICON
import net.furkanakdemir.noticeboard.config.ConfigRepository.Companion.KEY_EMPTY_TEXT
import net.furkanakdemir.noticeboard.config.ConfigRepository.Companion.KEY_ERROR_ICON
import net.furkanakdemir.noticeboard.config.ConfigRepository.Companion.KEY_ERROR_TEXT
import net.furkanakdemir.noticeboard.config.ConfigRepository.Companion.KEY_RELEASED_POSITION
import net.furkanakdemir.noticeboard.config.NoticeBoardConfigRepository
import net.furkanakdemir.noticeboard.data.datasource.NoticeBoardDataSourceFactory
import net.furkanakdemir.noticeboard.data.mapper.ChangeDomainMapper
import net.furkanakdemir.noticeboard.data.mapper.ReleaseDomainMapper
import net.furkanakdemir.noticeboard.data.model.Release
import net.furkanakdemir.noticeboard.data.model.ReleaseRaw
import net.furkanakdemir.noticeboard.data.repository.InMemoryNoticeBoardRepository
import net.furkanakdemir.noticeboard.data.repository.NoticeBoardRepository
import net.furkanakdemir.noticeboard.result.Result
import net.furkanakdemir.noticeboard.util.SingletonHolder
import net.furkanakdemir.noticeboard.util.color.ColorProvider
import net.furkanakdemir.noticeboard.util.color.NoticeBoardColorProvider
import net.furkanakdemir.noticeboard.util.io.DefaultFileReader
import net.furkanakdemir.noticeboard.util.io.FileReader
import net.furkanakdemir.noticeboard.util.mapper.ListMapper
import net.furkanakdemir.noticeboard.util.mapper.Mapper
import net.furkanakdemir.noticeboard.util.mapper.RealListMapper
import net.furkanakdemir.noticeboard.util.preference.PreferenceHelper
import net.furkanakdemir.noticeboard.util.preference.SharedPreferenceHelper

@Suppress("TooManyFunctions")
internal class InternalNoticeBoard private constructor(context: Context?) {

    private var noticeBoardRepository: NoticeBoardRepository
    private var configRepository: ConfigRepository

    private val defaultColorProvider: ColorProvider = NoticeBoardColorProvider()
    private val preferenceHelper: PreferenceHelper

    init {
        requireNotNull(context) { "Context cannot be null" }
        val factory = buildDataSourceFactory(context)

        noticeBoardRepository = InMemoryNoticeBoardRepository(factory)
        configRepository = NoticeBoardConfigRepository(defaultColorProvider)
        preferenceHelper = SharedPreferenceHelper(context)
    }

    private fun buildDataSourceFactory(context: Context): NoticeBoardDataSourceFactory {
        val releaseListDomainMapper = buildMapper()
        val fileReader: FileReader = DefaultFileReader(context)
        return NoticeBoardDataSourceFactory(fileReader, releaseListDomainMapper)
    }

    private fun buildMapper(): ListMapper<ReleaseRaw, Release> {
        val changeDomainMapper: Mapper<ReleaseRaw.ChangeRaw, Release.Change> = ChangeDomainMapper()
        val changeListDomainMapper: ListMapper<ReleaseRaw.ChangeRaw, Release.Change> =
            RealListMapper(changeDomainMapper)
        val releaseDomainMapper: Mapper<ReleaseRaw, Release> =
            ReleaseDomainMapper(changeListDomainMapper)
        return RealListMapper(releaseDomainMapper)
    }

    fun saveColorProvider(colorProvider: ColorProvider) =
        configRepository.saveColorProvider(colorProvider)

    fun fetchChanges(sourceType: Source) = noticeBoardRepository.fetchChanges(sourceType)

    fun getColorProvider(): ColorProvider = configRepository.getColorProvider()

    fun getChanges(): Result<List<Release>> = noticeBoardRepository.getChanges()

    fun setDefaultColorProvider() = configRepository.saveColorProvider(defaultColorProvider)

    fun getUnreleasedPosition(): Position = configRepository.getConfig(KEY_RELEASED_POSITION, TOP)

    fun setUnreleasedPosition(position: Position) =
        configRepository.setConfig(KEY_RELEASED_POSITION, position)

    fun getPinCount(): Int = preferenceHelper.getPinCount()

    fun plusPin() = preferenceHelper.plusPin()

    fun resetNumberOfPin() = preferenceHelper.resetPinCount()

    fun setTag(tag: String) {
        preferenceHelper.setTag(tag)
    }

    fun setEmptyText(text: String = "") {
        val emptyText = if (text.isEmpty()) TEXT_EMPTY_DEFAULT else text
        configRepository.setConfig(KEY_EMPTY_TEXT, emptyText)
    }

    fun setEmptyIcon(iconRes: Int = -1) {
        val icon = if (iconRes == -1) R.drawable.ic_logo else iconRes
        configRepository.setConfig(KEY_EMPTY_ICON, icon)
    }

    fun setErrorText(text: String = "") {
        val emptyText = if (text.isEmpty()) TEXT_ERROR_DEFAULT else text
        configRepository.setConfig(KEY_ERROR_TEXT, emptyText)
    }

    fun setErrorIcon(iconRes: Int = -1) {
        val icon = if (iconRes == -1) R.drawable.ic_logo else iconRes
        configRepository.setConfig(KEY_ERROR_ICON, icon)
    }

    fun getEmptyText(): String = configRepository.getConfig(KEY_EMPTY_TEXT, TEXT_EMPTY_DEFAULT)
    fun getEmptyIcon(): Int = configRepository.getConfig(KEY_EMPTY_ICON, R.drawable.ic_logo)
    fun getErrorText(): String = configRepository.getConfig(KEY_ERROR_TEXT, TEXT_ERROR_DEFAULT)
    fun getErrorIcon(): Int = configRepository.getConfig(KEY_ERROR_ICON, R.drawable.ic_logo)

    companion object : SingletonHolder<InternalNoticeBoard, Context>(::InternalNoticeBoard) {
        const val TEXT_EMPTY_DEFAULT = "Empty"
        const val TEXT_ERROR_DEFAULT = "Error"
    }
}
