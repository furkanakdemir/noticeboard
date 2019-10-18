package net.furkanakdemir.noticeboard.util.fakes

import net.furkanakdemir.noticeboard.ChangeType
import net.furkanakdemir.noticeboard.ChangeType.ADDED
import net.furkanakdemir.noticeboard.ChangeType.CHANGED
import net.furkanakdemir.noticeboard.ChangeType.DEPRECATED
import net.furkanakdemir.noticeboard.ChangeType.FIXED
import net.furkanakdemir.noticeboard.ChangeType.REMOVED
import net.furkanakdemir.noticeboard.ChangeType.SECURITY
import net.furkanakdemir.noticeboard.ChangeType.UNRELEASED
import net.furkanakdemir.noticeboard.DisplayOptions
import net.furkanakdemir.noticeboard.util.color.ColorProvider

class FakeColorProvider : ColorProvider {
    override fun getBackgroundColor(): Int = 7

    override fun getDescriptionColor(): Int = 8

    override fun getTitleColor(displayOptions: DisplayOptions): Int = 9

    override fun getChangeTypeBackgroundColor(changeType: ChangeType): Int {
        return when (changeType) {
            UNRELEASED -> -1
            ADDED -> 1
            CHANGED -> 2
            DEPRECATED -> 3
            REMOVED -> 4
            FIXED -> 5
            SECURITY -> 6
        }
    }
}
