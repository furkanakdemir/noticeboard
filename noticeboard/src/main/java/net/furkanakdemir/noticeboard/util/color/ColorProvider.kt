package net.furkanakdemir.noticeboard.util.color

import net.furkanakdemir.noticeboard.ChangeType
import net.furkanakdemir.noticeboard.DisplayOptions

interface ColorProvider {
    fun getChangeTypeBackgroundColor(changeType: ChangeType): Int
    fun getBackgroundColor(): Int
    fun getDescriptionColor(): Int
    fun getTitleColor(displayOptions: DisplayOptions): Int
}
