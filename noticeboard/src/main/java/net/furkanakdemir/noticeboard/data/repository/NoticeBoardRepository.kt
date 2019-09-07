package net.furkanakdemir.noticeboard.data.repository

import net.furkanakdemir.noticeboard.Source
import net.furkanakdemir.noticeboard.data.model.Release

interface NoticeBoardRepository {
    fun getChanges(): List<Release>
    fun saveChanges(newItems: List<Release>)
    fun fetchChanges(source: Source)
}