package net.furkanakdemir.noticeboard

import android.app.Dialog
import android.view.View

internal interface NoticeBoardBehavior {
    fun setBackgroundColor(colorId: Int)
}

internal class DialogNoticeBoardBehavior(private val dialog: Dialog) : NoticeBoardBehavior {
    override fun setBackgroundColor(colorId: Int) {
        dialog.window?.setBackgroundDrawableResource(colorId)
    }
}

internal class ActivityNoticeBoardBehavior(private val rootView: View) : NoticeBoardBehavior {
    override fun setBackgroundColor(colorId: Int) {
        rootView.setBackgroundColor(colorId)
    }
}