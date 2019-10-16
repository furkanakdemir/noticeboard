package net.furkanakdemir.noticeboardsample.util

import android.content.Context
import androidx.core.content.ContextCompat
import net.furkanakdemir.noticeboard.util.color.NoticeBoardColorProvider
import net.furkanakdemir.noticeboardsample.R

class CustomColorProvider(context: Context) : NoticeBoardColorProvider(context) {

    override var colorAdded: Int = ContextCompat.getColor(context, R.color.colorAccent)
    override var colorChanged: Int = ContextCompat.getColor(context, R.color.colorAccent)
    override var colorDeprecated: Int = ContextCompat.getColor(context, R.color.colorPrimary)
    override var colorRemoved: Int = ContextCompat.getColor(context, R.color.colorPrimary)
    override var colorFixed: Int = ContextCompat.getColor(context, R.color.colorPrimaryDark)
    override var colorSecurity: Int = ContextCompat.getColor(context, R.color.colorPrimaryDark)
}
