package net.furkanakdemir.noticeboard.core

import android.view.View
import androidx.recyclerview.widget.RecyclerView

internal abstract class BaseViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bind(item: T)
}
