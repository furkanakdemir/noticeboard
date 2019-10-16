package net.furkanakdemir.noticeboardsample.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import net.furkanakdemir.noticeboardsample.R
import net.furkanakdemir.noticeboardsample.ui.SampleAdapter.BaseViewHolder
import net.furkanakdemir.noticeboardsample.ui.SampleItem.Header
import net.furkanakdemir.noticeboardsample.ui.SampleItem.Main
import net.furkanakdemir.noticeboardsample.ui.SampleItem.Sample

class SampleAdapter constructor(val onSampleClick: (SampleItem) -> Unit) :
    RecyclerView.Adapter<BaseViewHolder<SampleItem>>() {

    var samples: MutableList<SampleItem> = mutableListOf()
        set(value) {
            field.clear()
            field.addAll(value)
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<SampleItem> {
        return when (viewType) {
            VIEW_TYPE_HEADER -> {
                HeaderViewHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(
                            R.layout.list_item_header,
                            parent,
                            false
                        )
                )
            }
            VIEW_TYPE_SAMPLE -> {
                SampleViewHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(
                            R.layout.list_item_sample,
                            parent,
                            false
                        )
                )
            }
            else -> {
                throw IllegalArgumentException("Unknown view type: $viewType")
            }
        }
    }

    override fun getItemCount(): Int = samples.size

    override fun getItemViewType(position: Int): Int {
        return when (samples[position]) {
            is Sample -> VIEW_TYPE_SAMPLE
            is Header -> VIEW_TYPE_HEADER
            is Main -> VIEW_TYPE_SAMPLE
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder<SampleItem>, position: Int) {
        holder.bind(samples[position])
        holder.itemView.setOnClickListener {
            onSampleClick(samples[position])
        }
    }

    companion object {
        const val VIEW_TYPE_HEADER = 0
        const val VIEW_TYPE_SAMPLE = 1
    }

    class SampleViewHolder(itemView: View) : BaseViewHolder<SampleItem>(itemView) {
        override fun bind(item: SampleItem) {
            itemView.findViewById<TextView>(R.id.sample).text = item.title
        }
    }

    class HeaderViewHolder(itemView: View) : BaseViewHolder<SampleItem>(itemView) {
        override fun bind(item: SampleItem) {
            itemView.findViewById<TextView>(R.id.header).text = item.title
        }
    }

    abstract class BaseViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {
        abstract fun bind(item: T)
    }
}
