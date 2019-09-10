package net.furkanakdemir.noticeboard.util.fakes

import net.furkanakdemir.noticeboard.ChangeType
import net.furkanakdemir.noticeboard.util.color.ColorProvider

class FakeColorProvider : ColorProvider {
    override fun getChangeTypeBackgroundColor(changeType: ChangeType): Int {
        return when (changeType) {
            ChangeType.ADDED -> 1
            ChangeType.CHANGED -> 2
            ChangeType.DEPRECATED -> 3
            ChangeType.REMOVED -> 4
            ChangeType.FIXED -> 5
            ChangeType.SECURITY -> 6
        }
    }
}
