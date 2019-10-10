package net.furkanakdemir.noticeboard.ui

import net.furkanakdemir.noticeboard.data.model.Release.Change
import net.furkanakdemir.noticeboard.ui.NoticeBoardItem.ChangeItem
import net.furkanakdemir.noticeboard.util.mapper.Mapper

internal class ChangeViewMapper : Mapper<List<Change>, List<ChangeItem>> {
    override fun map(input: List<Change>): List<ChangeItem> = input.map { change ->
        with(change) { ChangeItem(type, description) }
    }
}
