package net.furkanakdemir.noticeboard.util.color

import android.content.Context
import androidx.core.content.ContextCompat
import net.furkanakdemir.noticeboard.ChangeType
import net.furkanakdemir.noticeboard.ChangeType.ADDED
import net.furkanakdemir.noticeboard.ChangeType.CHANGED
import net.furkanakdemir.noticeboard.ChangeType.DEPRECATED
import net.furkanakdemir.noticeboard.ChangeType.FIXED
import net.furkanakdemir.noticeboard.ChangeType.REMOVED
import net.furkanakdemir.noticeboard.ChangeType.SECURITY
import net.furkanakdemir.noticeboard.R

open class NoticeBoardColorProvider(context: Context?) : ColorProvider {
    init {
        requireNotNull(context) { "Context cannot be null" }
    }

    protected open var colorAdded = ContextCompat.getColor(context!!, R.color.added)
    protected open var colorChanged = ContextCompat.getColor(context!!, R.color.changed)
    protected open var colorDeprecated = ContextCompat.getColor(context!!, R.color.deprecated)
    protected open var colorRemoved = ContextCompat.getColor(context!!, R.color.removed)
    protected open var colorFixed = ContextCompat.getColor(context!!, R.color.fixed)
    protected open var colorSecurity = ContextCompat.getColor(context!!, R.color.security)
    protected open var colorDefault = ContextCompat.getColor(context!!, R.color.default_type)

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
