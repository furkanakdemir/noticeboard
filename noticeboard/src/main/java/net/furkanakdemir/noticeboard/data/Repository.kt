package net.furkanakdemir.noticeboard.data

import net.furkanakdemir.noticeboard.NoticeBoardItem

interface Repository {
    fun getChanges(): List<NoticeBoardItem>
}