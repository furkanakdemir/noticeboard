package net.furkanakdemir.noticeboard.data.repository

import net.furkanakdemir.noticeboard.Source
import net.furkanakdemir.noticeboard.data.datasource.NoticeBoardDataSourceFactory
import net.furkanakdemir.noticeboard.data.model.Release
import javax.inject.Inject


class InMemoryNoticeBoardRepository @Inject constructor(
    private val noticeBoardDataSourceFactory: NoticeBoardDataSourceFactory
) : NoticeBoardRepository {

    private val items = mutableListOf<Release>()

    override fun getChanges(): List<Release> {
        return items
    }

    override fun saveChanges(newItems: List<Release>) {
        items.clear()
        items.addAll(newItems)
    }

    override fun fetchChanges(source: Source) {
        when (source) {
            is Source.Dynamic -> saveChanges(source.items)
            is Source.Xml -> {
                val dataSource = noticeBoardDataSourceFactory.createXmlDataSource(source.filepath)
                val releases = dataSource.getReleases()

                saveChanges(releases)
            }
            is Source.Json -> {

                val dataSource = noticeBoardDataSourceFactory.createJsonDataSource(source.filepath)
                val releases = dataSource.getReleases()

                saveChanges(releases)
            }
        }
    }
}