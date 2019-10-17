package net.furkanakdemir.noticeboard

import android.app.Dialog
import android.view.View

interface NoticeBoardBehavior {
    fun setBackgroundColor(colorId: Int)
}

class DialogNoticeBoardBehavior(private val dialog: Dialog) : NoticeBoardBehavior {
    override fun setBackgroundColor(colorId: Int) {
        dialog.window?.setBackgroundDrawableResource(colorId)
    }
}

class ActivityNoticeBoardBehavior(private val rootView: View) : NoticeBoardBehavior {
    override fun setBackgroundColor(colorId: Int) {
        rootView.setBackgroundColor(colorId)
    }
}