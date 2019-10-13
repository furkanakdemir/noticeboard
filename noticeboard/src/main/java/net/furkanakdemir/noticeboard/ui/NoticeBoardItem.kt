package net.furkanakdemir.noticeboard.ui

import net.furkanakdemir.noticeboard.ChangeType

internal sealed class NoticeBoardItem {

    class ReleaseHeader(val date: String, val version: String) : NoticeBoardItem()
    class ChangeItem(val type: ChangeType, val description: String) : NoticeBoardItem()

    class UnreleasedHeader(val title: String) : NoticeBoardItem()
    class UnreleasedItem(val description: String) : NoticeBoardItem()
}
