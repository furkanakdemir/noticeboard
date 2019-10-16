package net.furkanakdemir.noticeboardsample.util

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.State
import net.furkanakdemir.noticeboardsample.R
import net.furkanakdemir.noticeboardsample.ui.SampleAdapter.Companion.VIEW_TYPE_HEADER
import net.furkanakdemir.noticeboardsample.ui.SampleAdapter.Companion.VIEW_TYPE_SAMPLE

class CustomItemDecoration constructor(context: Context) : RecyclerView.ItemDecoration() {

    private val paint: Paint = Paint()
    private val heightInPx: Int = context.resources.getDimensionPixelSize(R.dimen.height_divider)

    init {
        paint.style = Paint.Style.FILL
        paint.color = ContextCompat.getColor(context, R.color.colorDivider)
    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: State) {
        val position = parent.getChildAdapterPosition(view)
        val viewType = parent.adapter?.getItemViewType(position)

        if (viewType == VIEW_TYPE_HEADER) {
            outRect.set(0, 0, 0, heightInPx)
        } else {
            outRect.setEmpty()
        }
    }

    override fun onDraw(canvas: Canvas, parent: RecyclerView, state: State) {
        for (i in 0 until parent.childCount) {
            val view = parent.getChildAt(i)
            val position = parent.getChildAdapterPosition(view)
            val viewType = parent.adapter?.getItemViewType(position)
            if (viewType == VIEW_TYPE_SAMPLE) {
                canvas.drawRect(
                    view.left.toFloat(),
                    view.bottom.toFloat(),
                    view.right.toFloat(),
                    view.bottom.toFloat() + heightInPx,
                    paint
                )
            }
        }
    }
}
