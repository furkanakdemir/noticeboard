package net.furkanakdemir.noticeboard.util.color

import android.content.Context
import androidx.core.content.ContextCompat
import net.furkanakdemir.noticeboard.ChangeType
import net.furkanakdemir.noticeboard.R

open class NoticeBoardColorProvider(private val context: Context) : ColorProvider {

    open var COLOR_ADDED = ContextCompat.getColor(context, R.color.added)
    open var COLOR_CHANGED = ContextCompat.getColor(context, R.color.changed)
    open var COLOR_DEPRECATED = ContextCompat.getColor(context, R.color.deprecated)
    open var COLOR_REMOVED = ContextCompat.getColor(context, R.color.removed)
    open var COLOR_FIXED = ContextCompat.getColor(context, R.color.fixed)
    open var COLOR_SECURITY = ContextCompat.getColor(context, R.color.security)

    override fun getChangeTypeBackgroundColor(changeType: ChangeType): Int {

        return when (changeType) {
            ChangeType.ADDED -> COLOR_ADDED
            ChangeType.CHANGED -> COLOR_CHANGED
            ChangeType.DEPRECATED -> COLOR_DEPRECATED
            ChangeType.REMOVED -> COLOR_REMOVED
            ChangeType.FIXED -> COLOR_FIXED
            ChangeType.SECURITY -> COLOR_SECURITY
        }
    }
}