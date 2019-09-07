package net.furkanakdemir.noticeboard.data.datasource

import net.furkanakdemir.noticeboard.data.model.Release
import net.furkanakdemir.noticeboard.data.model.ReleaseRaw
import net.furkanakdemir.noticeboard.util.io.FileReader
import net.furkanakdemir.noticeboard.util.mapper.ListMapper
import javax.inject.Inject

class NoticeBoardDataSourceFactory @Inject constructor(
    private val fileReader: FileReader,
    private val mapper: ListMapper<ReleaseRaw, Release>
) {
    fun createJsonDataSource(filepath: String): NoticeBoardDataSource =
        JsonNoticeBoardDataSource(
            fileReader,
            filepath,
            mapper
        )

    fun createXmlDataSource(filepath: String): NoticeBoardDataSource =
        XmlNoticeBoardDataSource(fileReader, filepath)
}