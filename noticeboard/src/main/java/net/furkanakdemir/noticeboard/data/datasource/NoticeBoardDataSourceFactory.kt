package net.furkanakdemir.noticeboard.data.datasource

import net.furkanakdemir.noticeboard.Source
import net.furkanakdemir.noticeboard.data.model.Release
import net.furkanakdemir.noticeboard.data.model.ReleaseRaw
import net.furkanakdemir.noticeboard.util.io.FileReader
import net.furkanakdemir.noticeboard.util.mapper.ListMapper
import javax.inject.Inject

class NoticeBoardDataSourceFactory @Inject constructor(
    private val fileReader: FileReader,
    private val mapper: ListMapper<ReleaseRaw, Release>
) {
    private fun createJsonDataSource(filepath: String): NoticeBoardDataSource =
        JsonNoticeBoardDataSource(fileReader, filepath, mapper)

    private fun createXmlDataSource(filepath: String): NoticeBoardDataSource =
        XmlNoticeBoardDataSource(fileReader, filepath)

    private fun createDynamicDataSource(list: List<Release>): NoticeBoardDataSource =
        DynamicNoticeBoardDataSource(list)

    fun createDataSource(source: Source): NoticeBoardDataSource {
        return when (source) {
            is Source.Dynamic -> createDynamicDataSource(source.items)
            is Source.Xml -> createXmlDataSource(source.filepath)
            is Source.Json -> createJsonDataSource(source.filepath)
        }
    }
}
