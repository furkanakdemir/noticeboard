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

    override fun getChanges(): Result<List<Release>> = result

    override fun fetchChanges(source: Source) {
        val dataSource = noticeBoardDataSourceFactory.createDataSource(source)

        result = try {
            val releases = dataSource.getReleases()
            Result.Success(releases)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}
