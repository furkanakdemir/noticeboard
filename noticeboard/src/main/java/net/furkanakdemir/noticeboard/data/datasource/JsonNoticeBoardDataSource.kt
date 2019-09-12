package net.furkanakdemir.noticeboard.data.datasource

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import net.furkanakdemir.noticeboard.data.model.Release
import net.furkanakdemir.noticeboard.data.model.ReleaseRaw
import net.furkanakdemir.noticeboard.util.io.FileReader
import net.furkanakdemir.noticeboard.util.mapper.ListMapper

internal class JsonNoticeBoardDataSource(
    private val fileReader: FileReader,
    private val filepath: String,
    private val mapper: ListMapper<ReleaseRaw, Release>
) : NoticeBoardDataSource {
    override fun getReleases(): List<Release> {
        val jsonString = fileReader.getFile(filepath)

        val moshi = Moshi.Builder().build()

        val type = Types.newParameterizedType(List::class.java, ReleaseRaw::class.java)
        val adapter: JsonAdapter<List<ReleaseRaw>> =
            moshi.adapter<List<ReleaseRaw>>(type).lenient()
        val releases = adapter.fromJson(jsonString.orEmpty())

        return mapper.map(releases.orEmpty())
    }
}
