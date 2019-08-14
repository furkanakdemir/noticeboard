package net.furkanakdemir.noticeboard.util.color

import android.content.Context
import androidx.core.content.ContextCompat
import net.furkanakdemir.noticeboard.ChangeType
import net.furkanakdemir.noticeboard.R

class NoticeBoardColorProvider(private val context: Context) : ColorProvider {
    override fun getChangeTypeBackgroundColor(changeType: ChangeType): Int {

        return when (changeType) {
            ChangeType.ADDED -> ContextCompat.getColor(context, R.color.added)
            ChangeType.CHANGED -> ContextCompat.getColor(context, R.color.changed)
            ChangeType.DEPRECATED -> ContextCompat.getColor(context, R.color.deprecated)
            ChangeType.REMOVED -> ContextCompat.getColor(context, R.color.removed)
            ChangeType.FIXED -> ContextCompat.getColor(context, R.color.fixed)
            ChangeType.SECURITY -> ContextCompat.getColor(context, R.color.security)
        }
    }
}