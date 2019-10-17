package net.furkanakdemir.noticeboard.util.color

import androidx.annotation.ColorRes
import net.furkanakdemir.noticeboard.ChangeType
import net.furkanakdemir.noticeboard.ChangeType.ADDED
import net.furkanakdemir.noticeboard.ChangeType.CHANGED
import net.furkanakdemir.noticeboard.ChangeType.DEPRECATED
import net.furkanakdemir.noticeboard.ChangeType.FIXED
import net.furkanakdemir.noticeboard.ChangeType.REMOVED
import net.furkanakdemir.noticeboard.ChangeType.SECURITY
import net.furkanakdemir.noticeboard.DisplayOptions
import net.furkanakdemir.noticeboard.DisplayOptions.ACTIVITY
import net.furkanakdemir.noticeboard.DisplayOptions.DIALOG
import net.furkanakdemir.noticeboard.R

open class NoticeBoardColorProvider : ColorProvider {

    @ColorRes
    protected open var colorAdded = R.color.added

    @ColorRes
    protected open var colorChanged = R.color.changed

    @ColorRes
    protected open var colorDeprecated = R.color.deprecated

    @ColorRes
    protected open var colorRemoved = R.color.removed

    @ColorRes
    protected open var colorFixed = R.color.fixed

    @ColorRes
    protected open var colorSecurity = R.color.security

    @ColorRes
    protected open var colorDefault = R.color.change_type_background_default

    @ColorRes
    protected open var colorBackground = R.color.background_default

    @ColorRes
    protected open var colorDescriptionText = R.color.noticeboard_description_text_default

    @ColorRes
    protected open var colorTitleActivity = R.color.noticeboard_title_text_activity_default

    @ColorRes
    protected open var colorTitleDialog = R.color.noticeboard_title_text_dialog_default

    override fun getBackgroundColor(): Int = colorBackground

    override fun getDescriptionColor(): Int = colorDescriptionText

    override fun getTitleColor(displayOptions: DisplayOptions): Int {
        return when (displayOptions) {
            ACTIVITY -> colorTitleActivity
            DIALOG -> colorTitleDialog
        }
    }

    override fun getChangeTypeBackgroundColor(changeType: ChangeType): Int {

        return when (changeType) {
            ADDED -> colorAdded
            CHANGED -> colorChanged
            DEPRECATED -> colorDeprecated
            REMOVED -> colorRemoved
            FIXED -> colorFixed
            SECURITY -> colorSecurity
            else -> colorDefault
        }
    }
}
