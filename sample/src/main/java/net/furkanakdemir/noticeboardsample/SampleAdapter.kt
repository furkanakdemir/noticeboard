package net.furkanakdemir.noticeboardsample

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SampleAdapter constructor(private val callback: OnSampleClickCallback) :
    RecyclerView.Adapter<SampleAdapter.SampleViewHolder>() {

    var samples: MutableList<String> = mutableListOf()
        set(value) {
            field.clear()
            field.addAll(value)
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        SampleViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.list_item_sample, parent, false)
        )

    override fun getItemCount(): Int = samples.size

    override fun onBindViewHolder(holder: SampleViewHolder, position: Int) {
        holder.bind(samples[position])
        holder.itemView.setOnClickListener {
            callback.onClicked(position)
        }
    }

    class SampleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(title: String) {
            itemView.findViewById<TextView>(R.id.sample).text = title
        }
    }

    interface OnSampleClickCallback {
        fun onClicked(position: Int)
    }
}




