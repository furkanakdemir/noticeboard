package net.furkanakdemir.noticeboard.data.datasource

import net.furkanakdemir.noticeboard.data.model.Release

internal class DynamicNoticeBoardDataSource(val list: List<Release>) : NoticeBoardDataSource {
    override fun getReleases(): List<Release> = list
}
