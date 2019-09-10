package net.furkanakdemir.noticeboardsample

import android.content.Context
import androidx.core.content.ContextCompat
import net.furkanakdemir.noticeboard.util.color.NoticeBoardColorProvider

class CustomColorProvider(private val context: Context) : NoticeBoardColorProvider(context) {
    override var colorAdded: Int = ContextCompat.getColor(context, R.color.colorAccent)
}
