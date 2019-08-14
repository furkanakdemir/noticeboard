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
) : RecyclerView.Adapter<BaseViewHolder<NoticeBoardItem>>() {

    var releaseList: MutableList<NoticeBoardItem> = mutableListOf()
        set(value) {
            releaseList.clear()
            releaseList.addAll(value)
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<NoticeBoardItem> {

        return when (viewType) {
            VIEW_TYPE_HEADER -> HeaderViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.list_item_change_log_header, parent, false)
            )
            VIEW_TYPE_ITEM -> ChangeViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.list_item_change_log, parent, false)
            )
            else -> ChangeViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.list_item_change_log, parent, false)
            )
        }
    }

    override fun getItemCount() = releaseList.size

    override fun onBindViewHolder(holder: BaseViewHolder<NoticeBoardItem>, position: Int) {
        when (val noticeBoardItem = releaseList[position]) {
            is NoticeBoardItem.ReleaseHeader -> {
                holder.bind(noticeBoardItem)
            }
            is NoticeBoardItem.ChangeItem -> {
                holder.bind(noticeBoardItem)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (releaseList[position]) {
            is NoticeBoardItem.ReleaseHeader -> VIEW_TYPE_HEADER
            else -> VIEW_TYPE_ITEM
        }
    }

    companion object {
        private const val VIEW_TYPE_HEADER = 1
        private const val VIEW_TYPE_ITEM = 2
    }


    inner class HeaderViewHolder(itemView: View) : BaseViewHolder<NoticeBoardItem>(itemView) {
        override fun bind(item: NoticeBoardItem) {

            val model = item as NoticeBoardItem.ReleaseHeader

            itemView.findViewById<TextView>(R.id.dateTextView).text = model.date
            itemView.findViewById<TextView>(R.id.versionTextView).text = model.version
        }
    }

    inner class ChangeViewHolder(itemView: View) : BaseViewHolder<NoticeBoardItem>(itemView) {
        override fun bind(item: NoticeBoardItem) {
            val model = item as NoticeBoardItem.ChangeItem

            itemView.findViewById<TextView>(R.id.change_description).text = model.description
            itemView.findViewById<TextView>(R.id.change_type).text = model.type.name

            val colorId = colorProvider.getChangeTypeBackgroundColor(model.type)

            DrawableCompat.setTint(itemView.findViewById<TextView>(R.id.change_type).background, colorId)

        }

    }
}



