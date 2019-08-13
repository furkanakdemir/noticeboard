package net.furkanakdemir.noticeboard.data

import net.furkanakdemir.noticeboard.ChangeType
import net.furkanakdemir.noticeboard.NoticeBoardItem

class NoticeBoardRepository : Repository {
    override fun getChanges(): List<NoticeBoardItem> {
        return mutableListOf(
            NoticeBoardItem.ReleaseHeader("16 Sep 2019", "2.0.0"),
            NoticeBoardItem.ChangeItem(ChangeType.ADDED, "New Login Page"),
            NoticeBoardItem.ChangeItem(ChangeType.FIXED, "Crash in Payment"),
            NoticeBoardItem.ChangeItem(ChangeType.FIXED, "Crash in Payment"),
            NoticeBoardItem.ChangeItem(ChangeType.FIXED, "Crash in Payment"),
            NoticeBoardItem.ReleaseHeader("28 Jun 2019", "1.9.1"),
            NoticeBoardItem.ChangeItem(ChangeType.ADDED, "New Login Page"),
            NoticeBoardItem.ChangeItem(ChangeType.SECURITY, "Crash in Payment"),
            NoticeBoardItem.ChangeItem(ChangeType.CHANGED, "Crash in Payment"),
            NoticeBoardItem.ChangeItem(ChangeType.REMOVED, "Crash in Payment"),
            NoticeBoardItem.ChangeItem(ChangeType.DEPRECATED, "Crash in Payment")
        )
    }
}