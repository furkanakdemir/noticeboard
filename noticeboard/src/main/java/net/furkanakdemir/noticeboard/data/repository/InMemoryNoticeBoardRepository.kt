package net.furkanakdemir.noticeboard.data.repository

import net.furkanakdemir.noticeboard.Source
import net.furkanakdemir.noticeboard.data.datasource.NoticeBoardDataSourceFactory
import net.furkanakdemir.noticeboard.data.model.Release
import net.furkanakdemir.noticeboard.result.Result
import javax.inject.Inject


class InMemoryNoticeBoardRepository @Inject constructor(
    private val noticeBoardDataSourceFactory: NoticeBoardDataSourceFactory
) : NoticeBoardRepository {

    private lateinit var result: Result<List<Release>>

    override fun getChanges(): Result<List<Release>> {
        return result
    }

    override fun fetchChanges(source: Source) {
        when (source) {
            is Source.Dynamic -> result = Result.Success(source.items)
            is Source.Xml -> {
                val dataSource = noticeBoardDataSourceFactory.createXmlDataSource(source.filepath)
                val releases = dataSource.getReleases()

                result = Result.Success(releases)
            }
            is Source.Json -> {

                val dataSource = noticeBoardDataSourceFactory.createJsonDataSource(source.filepath)
                val releases = dataSource.getReleases()

                result = Result.Success(releases)
            }
        }
    }
}