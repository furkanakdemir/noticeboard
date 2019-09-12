package net.furkanakdemir.noticeboard.ui

import net.furkanakdemir.noticeboard.ChangeType
import net.furkanakdemir.noticeboard.data.model.Release
import net.furkanakdemir.noticeboard.util.mapper.Mapper
import javax.inject.Inject

class ReleaseViewMapper @Inject constructor() : Mapper<List<Release>, List<NoticeBoardItem>> {
    override fun map(input: List<Release>): List<NoticeBoardItem> {
        val items = mutableListOf<NoticeBoardItem>()

        input.forEach {

            items += NoticeBoardItem.ReleaseHeader(it.date, it.version)

            it.changes.forEach { change ->
                items += NoticeBoardItem.ChangeItem(
                    ChangeType.values()[change.type],
                    change.description
                )
            }
        }

        return items
    }
}