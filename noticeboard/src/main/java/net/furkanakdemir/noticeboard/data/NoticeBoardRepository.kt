package net.furkanakdemir.noticeboard.data

import net.furkanakdemir.noticeboard.ChangeType
import net.furkanakdemir.noticeboard.NoticeBoardItem

class NoticeBoardRepository : Repository {
    override fun getChanges(): List<NoticeBoardItem> {
        return mutableListOf(
            NoticeBoardItem.ReleaseHeader("16 Sep 2019", "2.0.0"),
            NoticeBoardItem.ChangeItem(ChangeType.ADDED, "New Login Page"),
            NoticeBoardItem.ChangeItem(
                ChangeType.FIXED,
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."
            ),
            NoticeBoardItem.ChangeItem(ChangeType.FIXED, "Crash in Payment"),
            NoticeBoardItem.ChangeItem(ChangeType.FIXED, "Crash in Payment"),
            NoticeBoardItem.ReleaseHeader("28 Jun 2019", "1.9.1"),
            NoticeBoardItem.ChangeItem(ChangeType.ADDED, "New Login Page"),
            NoticeBoardItem.ChangeItem(ChangeType.SECURITY, "Crash in Payment"),
            NoticeBoardItem.ChangeItem(ChangeType.CHANGED, "Crash in Payment"),
            NoticeBoardItem.ChangeItem(ChangeType.REMOVED, "Crash in Payment"),
            NoticeBoardItem.ChangeItem(
                ChangeType.DEPRECATED,
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."
            ),
            NoticeBoardItem.ReleaseHeader("28 Mar 2019", "1.4.1"),
            NoticeBoardItem.ChangeItem(ChangeType.ADDED, "New Login Page"),
            NoticeBoardItem.ChangeItem(ChangeType.SECURITY, "Crash in Payment"),
            NoticeBoardItem.ChangeItem(ChangeType.CHANGED, "Crash in Payment"),
            NoticeBoardItem.ChangeItem(ChangeType.REMOVED, "Crash in Payment"),
            NoticeBoardItem.ChangeItem(ChangeType.DEPRECATED, "Crash in Payment"),
            NoticeBoardItem.ReleaseHeader("28 Jan 2019", "1.2.3"),
            NoticeBoardItem.ChangeItem(ChangeType.ADDED, "New Login Page"),
            NoticeBoardItem.ChangeItem(ChangeType.SECURITY, "Crash in Payment"),
            NoticeBoardItem.ChangeItem(ChangeType.CHANGED, "Crash in Payment"),
            NoticeBoardItem.ChangeItem(ChangeType.REMOVED, "Crash in Payment"),
            NoticeBoardItem.ChangeItem(ChangeType.DEPRECATED, "Crash in Payment")
        )
    }
}