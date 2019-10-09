package net.furkanakdemir.noticeboard.ui

import net.furkanakdemir.noticeboard.data.model.Release
import net.furkanakdemir.noticeboard.ui.NoticeBoardItem.ChangeItem
import net.furkanakdemir.noticeboard.ui.NoticeBoardItem.ReleaseHeader
import net.furkanakdemir.noticeboard.ui.NoticeBoardItem.UnreleasedHeader
import net.furkanakdemir.noticeboard.ui.NoticeBoardItem.UnreleasedItem
import net.furkanakdemir.noticeboard.util.mapper.Mapper

internal class ReleaseViewMapper : Mapper<List<Release>, List<NoticeBoardItem>> {

    override fun map(input: List<Release>): List<NoticeBoardItem> {
        val items = mutableListOf<NoticeBoardItem>()

        input.forEach {

            if (it.isReleased) {
                items += ReleaseHeader(it.date, it.version)

                it.changes.forEach { change ->
                    with(change) { items += ChangeItem(type, description) }
                }

            } else {
                items += UnreleasedHeader(it.date)

                it.changes.forEach { change -> items += UnreleasedItem(change.description) }
            }
        }

        return items
    }
}
