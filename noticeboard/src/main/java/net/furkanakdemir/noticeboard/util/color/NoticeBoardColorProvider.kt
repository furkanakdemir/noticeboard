package net.furkanakdemir.noticeboard.util.color

import android.content.Context
import androidx.core.content.ContextCompat
import net.furkanakdemir.noticeboard.ChangeType
import net.furkanakdemir.noticeboard.R

open class NoticeBoardColorProvider(context: Context) : ColorProvider {

    protected open var colorAdded = ContextCompat.getColor(context, R.color.added)
    protected open var colorChanged = ContextCompat.getColor(context, R.color.changed)
    protected open var colorDeprecated = ContextCompat.getColor(context, R.color.deprecated)
    protected open var colorRemoved = ContextCompat.getColor(context, R.color.removed)
    protected open var colorFixed = ContextCompat.getColor(context, R.color.fixed)
    protected open var colorSecurity = ContextCompat.getColor(context, R.color.security)

    override fun getChangeTypeBackgroundColor(changeType: ChangeType): Int {

        return when (changeType) {
            ChangeType.ADDED -> colorAdded
            ChangeType.CHANGED -> colorChanged
            ChangeType.DEPRECATED -> colorDeprecated
            ChangeType.REMOVED -> colorRemoved
            ChangeType.FIXED -> colorFixed
            ChangeType.SECURITY -> colorSecurity
        }
    }
}
