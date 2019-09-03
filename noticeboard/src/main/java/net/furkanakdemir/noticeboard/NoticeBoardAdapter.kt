package net.furkanakdemir.noticeboard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.graphics.drawable.DrawableCompat
import androidx.recyclerview.widget.RecyclerView
import net.furkanakdemir.noticeboard.core.BaseViewHolder
import net.furkanakdemir.noticeboard.util.color.ColorProvider

class NoticeBoardAdapter constructor(
    val colorProvider: ColorProvider
) : RecyclerView.Adapter<BaseViewHolder<*>>() {

    var releaseList: MutableList<NoticeBoardItem> = mutableListOf()
        set(value) {
            releaseList.clear()
            releaseList.addAll(value)
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {

        return when (viewType) {
            VIEW_TYPE_HEADER -> HeaderViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.list_item_change_log_header, parent, false)
            )
            VIEW_TYPE_ITEM -> ChangeViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.list_item_change_log, parent, false)
            )
            else -> throw IllegalArgumentException("Invalid view type $viewType")
        }
    }

    override fun getItemCount() = releaseList.size

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        val noticeBoardItem = releaseList[position]
        when (holder) {
            is HeaderViewHolder -> holder.bind(noticeBoardItem as NoticeBoardItem.ReleaseHeader)
            is ChangeViewHolder -> holder.bind(noticeBoardItem as NoticeBoardItem.ChangeItem)
            else -> throw IllegalArgumentException("Invalid view holder $holder")
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (releaseList[position]) {
            is NoticeBoardItem.ReleaseHeader -> VIEW_TYPE_HEADER
            is NoticeBoardItem.ChangeItem -> VIEW_TYPE_ITEM
        }
    }

    companion object {
        private const val VIEW_TYPE_HEADER = 1
        private const val VIEW_TYPE_ITEM = 2
    }


    inner class HeaderViewHolder(itemView: View) : BaseViewHolder<NoticeBoardItem.ReleaseHeader>(itemView) {
        override fun bind(item: NoticeBoardItem.ReleaseHeader) {
            itemView.findViewById<TextView>(R.id.dateTextView).text = item.date
            itemView.findViewById<TextView>(R.id.versionTextView).text = item.version
        }
    }

    inner class ChangeViewHolder(itemView: View) : BaseViewHolder<NoticeBoardItem.ChangeItem>(itemView) {
        override fun bind(item: NoticeBoardItem.ChangeItem) {
            itemView.findViewById<TextView>(R.id.change_description).text = item.description
            itemView.findViewById<TextView>(R.id.change_type).text = item.type.name

            val colorId = colorProvider.getChangeTypeBackgroundColor(item.type)

            DrawableCompat.setTint(itemView.findViewById<TextView>(R.id.change_type).background, colorId)
        }
    }
}
