package net.furkanakdemir.noticeboard

import net.furkanakdemir.noticeboard.config.ConfigRepository
import net.furkanakdemir.noticeboard.config.NoticeBoardConfigRepository
import net.furkanakdemir.noticeboard.data.datasource.NoticeBoardDataSourceFactory
import net.furkanakdemir.noticeboard.data.mapper.ChangeDomainMapper
import net.furkanakdemir.noticeboard.data.mapper.ReleaseDomainMapper
import net.furkanakdemir.noticeboard.data.model.Release
import net.furkanakdemir.noticeboard.data.repository.InMemoryNoticeBoardRepository
import net.furkanakdemir.noticeboard.data.repository.NoticeBoardRepository
import net.furkanakdemir.noticeboard.result.Result
import net.furkanakdemir.noticeboard.util.color.ColorProvider
import net.furkanakdemir.noticeboard.util.io.FileReader
import net.furkanakdemir.noticeboard.util.mapper.RealListMapper

internal object InternalNoticeBoard {

    private lateinit var noticeBoardRepository: NoticeBoardRepository
    private lateinit var configRepository: ConfigRepository

    lateinit var fileReader: FileReader
    lateinit var defaultColorProvider: ColorProvider
    lateinit var factory: NoticeBoardDataSourceFactory

    fun setup() {
        factory = NoticeBoardDataSourceFactory(
            fileReader,
            RealListMapper(ReleaseDomainMapper(RealListMapper(ChangeDomainMapper())))
        )
        noticeBoardRepository = InMemoryNoticeBoardRepository(factory)
        configRepository = NoticeBoardConfigRepository(defaultColorProvider)
    }

    fun saveColorProvider(colorProvider: ColorProvider) {
        configRepository.saveColorProvider(colorProvider)
    }

    fun fetchChanges(sourceType: Source) {
        noticeBoardRepository.fetchChanges(sourceType)
    }

    fun getColorProvider(): ColorProvider {
        return configRepository.getColorProvider()
    }

    fun getChanges(): Result<List<Release>> {
        return noticeBoardRepository.getChanges()
    }
}