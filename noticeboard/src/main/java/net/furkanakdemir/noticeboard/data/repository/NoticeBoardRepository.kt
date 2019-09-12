package net.furkanakdemir.noticeboard.data.repository

import net.furkanakdemir.noticeboard.Source
import net.furkanakdemir.noticeboard.data.model.Release
import net.furkanakdemir.noticeboard.result.Result

internal interface NoticeBoardRepository {
    fun getChanges(): Result<List<Release>>
    fun fetchChanges(source: Source)
}
