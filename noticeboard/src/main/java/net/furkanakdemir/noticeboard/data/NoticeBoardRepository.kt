package net.furkanakdemir.noticeboard.data

import net.furkanakdemir.noticeboard.NoticeBoardItem

interface NoticeBoardRepository {
    fun getChanges(): List<NoticeBoardItem>
    fun saveChanges(items: List<NoticeBoardItem>)
}