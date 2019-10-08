package net.furkanakdemir.noticeboard.ui

import net.furkanakdemir.noticeboard.Position
import net.furkanakdemir.noticeboard.Position.NONE
import net.furkanakdemir.noticeboard.data.model.Release
import net.furkanakdemir.noticeboard.ui.NoticeBoardItem.ChangeItem
import net.furkanakdemir.noticeboard.ui.NoticeBoardItem.ReleaseHeader
import net.furkanakdemir.noticeboard.ui.NoticeBoardItem.UnreleasedHeader
import net.furkanakdemir.noticeboard.ui.NoticeBoardItem.UnreleasedItem
import net.furkanakdemir.noticeboard.util.mapper.Mapper

internal class ReleaseViewMapper(private val unreleasedPosition: Position = NONE) :
    Mapper<List<Release>, List<NoticeBoardItem>> {

    private var isHeaderAdded: Boolean = false

    override fun map(input: List<Release>): List<NoticeBoardItem> {
        val items = mutableListOf<NoticeBoardItem>()
        val unreleasedItems = mutableListOf<NoticeBoardItem>()


        input.forEach {

            if (it.isReleased) {
                addReleases(items, it)
            } else {
                if (!unreleasedPosition.isNone()) {
                    addUnreleased(unreleasedItems, it)
                } else {
                    unreleasedItems += UnreleasedHeader(it.date)

                    it.changes.forEach { change ->
                        unreleasedItems += UnreleasedItem(change.description)
                    }

                    items.addAll(unreleasedItems)
                    unreleasedItems.clear()
                }
            }
        }

        if (unreleasedPosition.isTop()) {
            items.addAll(INDEX_UNRELEASED_INSERT, unreleasedItems)
        }

        if (unreleasedPosition.isBottom()) {
            items.addAll(unreleasedItems)
        }

        return items
    }

    private fun addReleases(
        items: MutableList<NoticeBoardItem>,
        it: Release
    ) {
        items += ReleaseHeader(it.date, it.version)

        it.changes.forEach { change ->
            items += ChangeItem(
                change.type,
                change.description
            )
        }
    }

    private fun addUnreleased(
        unreleasedItems: MutableList<NoticeBoardItem>,
        it: Release
    ) {
        if (!isHeaderAdded) {
            unreleasedItems += UnreleasedHeader(it.date)
            isHeaderAdded = true
        }

        it.changes.forEach { change ->
            unreleasedItems += UnreleasedItem(change.description)
        }
    }

    companion object {
        private const val INDEX_UNRELEASED_INSERT = 0
    }
}
