package net.furkanakdemir.noticeboard

import android.app.Dialog
import android.graphics.PorterDuff.Mode.SRC_ATOP
import android.graphics.PorterDuffColorFilter
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat

internal interface NoticeBoardBehavior {
    fun setBackgroundColor(colorId: Int)
    fun setTitleColor(colorId: Int)
    fun setTitleText(title: String)
}

internal class DialogNoticeBoardBehavior(
    private val dialog: Dialog,
    private val titleView: TextView
) : NoticeBoardBehavior {
    override fun setBackgroundColor(colorId: Int) {
        dialog.window?.setBackgroundDrawable(ColorDrawable(colorId))
    }

    override fun setTitleColor(colorId: Int) {
        titleView.setTextColor(colorId)
    }

    override fun setTitleText(title: String) {
        titleView.text = title
    }
}

internal class ActivityNoticeBoardBehavior(private val activity: AppCompatActivity) :
    NoticeBoardBehavior {

    private val toolbar: Toolbar = activity.findViewById(R.id.notice_board_toolbar)
    private val rootView: View = activity.findViewById(R.id.rootLayout)

    override fun setBackgroundColor(colorId: Int) {
        rootView.setBackgroundColor(colorId)
    }

    override fun setTitleColor(colorId: Int) {
        toolbar.setTitleTextColor(colorId)

        val arrow = ContextCompat.getDrawable(activity, R.drawable.ic_back)
        arrow?.colorFilter = PorterDuffColorFilter(colorId, SRC_ATOP)
        toolbar.navigationIcon = arrow
    }

    override fun setTitleText(title: String) {
        activity.supportActionBar?.title = title
    }
}
