package net.furkanakdemir.noticeboard.ui

import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import net.furkanakdemir.noticeboard.R
import net.furkanakdemir.noticeboard.core.BaseViewHolder
import net.furkanakdemir.noticeboard.ui.NoticeBoardItem.ChangeItem
import net.furkanakdemir.noticeboard.ui.NoticeBoardItem.ReleaseHeader
import net.furkanakdemir.noticeboard.ui.NoticeBoardItem.UnreleasedHeader
import net.furkanakdemir.noticeboard.ui.NoticeBoardItem.UnreleasedItem
import net.furkanakdemir.noticeboard.util.color.ColorProvider

internal class NoticeBoardAdapter(val colorProvider: ColorProvider) :
    RecyclerView.Adapter<BaseViewHolder<*>>() {

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
            VIEW_TYPE_UNRELEASED_HEADER -> UnreleasedHeaderViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.list_item_unreleased_header, parent, false)
            )
            VIEW_TYPE_UNRELEASED_ITEM -> UnreleasedViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.list_item_unreleased, parent, false)
            )
            else -> throw IllegalArgumentException("Invalid view type $viewType")
        }
    }

    override fun getItemCount() = releaseList.size

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        val noticeBoardItem = releaseList[position]
        when (holder) {
            is HeaderViewHolder -> holder.bind(noticeBoardItem as ReleaseHeader)
            is ChangeViewHolder -> holder.bind(noticeBoardItem as ChangeItem)
            is UnreleasedHeaderViewHolder -> holder.bind(noticeBoardItem as UnreleasedHeader)
            is UnreleasedViewHolder -> holder.bind(noticeBoardItem as UnreleasedItem)
            else -> throw IllegalArgumentException("Invalid view holder $holder")
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (releaseList[position]) {
            is ReleaseHeader -> VIEW_TYPE_HEADER
            is ChangeItem -> VIEW_TYPE_ITEM
            is UnreleasedHeader -> VIEW_TYPE_UNRELEASED_HEADER
            is UnreleasedItem -> VIEW_TYPE_UNRELEASED_ITEM
        }
    }

    companion object {
        private const val VIEW_TYPE_HEADER = 1
        private const val VIEW_TYPE_ITEM = 2
        private const val VIEW_TYPE_UNRELEASED_HEADER = 3
        private const val VIEW_TYPE_UNRELEASED_ITEM = 4
    }

    inner class HeaderViewHolder(itemView: View) :
        BaseViewHolder<ReleaseHeader>(itemView) {
        override fun bind(item: ReleaseHeader) {
            itemView.findViewById<TextView>(R.id.dateTextView).text = item.date
            itemView.findViewById<TextView>(R.id.versionTextView).text = item.version
        }
    }

    inner class ChangeViewHolder(itemView: View) :
        BaseViewHolder<ChangeItem>(itemView) {
        override fun bind(item: ChangeItem) {
            itemView.findViewById<TextView>(R.id.change_description).text = item.description
            itemView.findViewById<TextView>(R.id.change_type).text = item.type.name

            val colorId = colorProvider.getChangeTypeBackgroundColor(item.type)
            val changeTypeTextView = itemView.findViewById<TextView>(R.id.change_type)
            changeTypeTextView.background.colorFilter =
                PorterDuffColorFilter(colorId, PorterDuff.Mode.SRC_IN)
        }
    }

    inner class UnreleasedHeaderViewHolder(itemView: View) :
        BaseViewHolder<UnreleasedHeader>(itemView) {
        override fun bind(item: UnreleasedHeader) {
            itemView.findViewById<TextView>(R.id.titleTextView).text = item.title
        }
    }

    inner class UnreleasedViewHolder(itemView: View) :
        BaseViewHolder<UnreleasedItem>(itemView) {
        override fun bind(item: UnreleasedItem) {
            itemView.findViewById<TextView>(R.id.unreleased_description).text = item.description
        }
    }
}
