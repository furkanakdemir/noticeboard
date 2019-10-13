package net.furkanakdemir.noticeboard.ui

import net.furkanakdemir.noticeboard.data.model.Release.Change
import net.furkanakdemir.noticeboard.ui.NoticeBoardItem.UnreleasedItem
import net.furkanakdemir.noticeboard.util.mapper.Mapper

internal class UnreleaseChangeViewMapper : Mapper<List<Change>, List<UnreleasedItem>> {
    override fun map(input: List<Change>): List<UnreleasedItem> = input.map {
        UnreleasedItem(it.description)
    }
}
