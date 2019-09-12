package net.furkanakdemir.noticeboard.di

import android.content.Context
import dagger.Module
import dagger.Provides
import net.furkanakdemir.noticeboard.config.ConfigRepository
import net.furkanakdemir.noticeboard.config.NoticeBoardConfigRepository
import net.furkanakdemir.noticeboard.data.datasource.NoticeBoardDataSourceFactory
import net.furkanakdemir.noticeboard.data.mapper.ChangeDomainMapper
import net.furkanakdemir.noticeboard.data.mapper.ReleaseDomainMapper
import net.furkanakdemir.noticeboard.data.model.Release
import net.furkanakdemir.noticeboard.data.model.ReleaseRaw
import net.furkanakdemir.noticeboard.data.repository.InMemoryNoticeBoardRepository
import net.furkanakdemir.noticeboard.data.repository.NoticeBoardRepository
import net.furkanakdemir.noticeboard.ui.NoticeBoardItem
import net.furkanakdemir.noticeboard.ui.ReleaseViewMapper
import net.furkanakdemir.noticeboard.util.color.ColorProvider
import net.furkanakdemir.noticeboard.util.color.NoticeBoardColorProvider
import net.furkanakdemir.noticeboard.util.io.DefaultFileReader
import net.furkanakdemir.noticeboard.util.io.FileReader
import net.furkanakdemir.noticeboard.util.mapper.ListMapper
import net.furkanakdemir.noticeboard.util.mapper.Mapper
import net.furkanakdemir.noticeboard.util.mapper.RealListMapper


@Module
class NoticeBoardModule constructor(val context: Context) {

    @Provides
    @NoticeBoardScope
    fun provideContext(): Context = context

    @Provides
    fun provideFileReader(context: Context): FileReader = DefaultFileReader(context)

    @Provides
    @NoticeBoardScope
    fun provideNoticeBoardRepository(factory: NoticeBoardDataSourceFactory): NoticeBoardRepository =
        InMemoryNoticeBoardRepository(factory)

    @Provides
    fun provideNoticeBoardDataSourceFactory(
        fileReader: FileReader,
        mapper: ListMapper<ReleaseRaw, Release>
    ): NoticeBoardDataSourceFactory =
        NoticeBoardDataSourceFactory(
            fileReader,
            mapper
        )

    @Provides
    @NoticeBoardScope
    fun provideColorProvider(context: Context): ColorProvider =
        NoticeBoardColorProvider(context)

    @Provides
    @NoticeBoardScope
    fun provideConfigRepository(colorProvider: ColorProvider): ConfigRepository =
        NoticeBoardConfigRepository(colorProvider)

    @Provides
    fun provideReleaseListMapper(releaseDomainMapper: ReleaseDomainMapper):
            ListMapper<ReleaseRaw, Release> = RealListMapper(releaseDomainMapper)

    @Provides
    fun provideChangeListMapper(changeDomainMapper: ChangeDomainMapper):
            ListMapper<ReleaseRaw.ChangeRaw, Release.Change> =
        RealListMapper(changeDomainMapper)

    @Provides
    fun provideChangeMapper(): Mapper<ReleaseRaw.ChangeRaw, Release.Change> =
        ChangeDomainMapper()

    @Provides
    fun provideReleaseViewMapper(): Mapper<List<Release>, List<NoticeBoardItem>> =
        ReleaseViewMapper()
}