package net.furkanakdemir.noticeboard.util.color

import net.furkanakdemir.noticeboard.ChangeType

interface ColorProvider {
    fun getChangeTypeBackgroundColor(changeType: ChangeType): Int
    fun getBackgroundColor(): Int
}
