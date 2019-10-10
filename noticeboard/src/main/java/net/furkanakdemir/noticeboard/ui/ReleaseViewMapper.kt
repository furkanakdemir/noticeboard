package net.furkanakdemir.noticeboard.ui

import net.furkanakdemir.noticeboard.data.model.Release
import net.furkanakdemir.noticeboard.ui.NoticeBoardItem.ReleaseHeader
import net.furkanakdemir.noticeboard.ui.NoticeBoardItem.UnreleasedHeader
import net.furkanakdemir.noticeboard.util.mapper.Mapper

internal class ReleaseViewMapper : Mapper<List<Release>, List<NoticeBoardItem>> {

    private val changeViewMapper = ChangeViewMapper()
    private val unreleaseChangeViewMapper = UnreleaseChangeViewMapper()

    override fun map(input: List<Release>): List<NoticeBoardItem> {
        val items = mutableListOf<NoticeBoardItem>()

        input.forEach {

            if (it.isReleased) {
                items += ReleaseHeader(it.date, it.version)

                items.addAll(changeViewMapper.map(it.changes))
            } else {
                items += UnreleasedHeader(it.date)

                items.addAll(unreleaseChangeViewMapper.map(it.changes))
            }
        }

        return items
    }
}
