package net.furkanakdemir.noticeboard.data.datasource

import net.furkanakdemir.noticeboard.data.model.Release

interface NoticeBoardDataSource {
    fun getReleases(): List<Release>
}