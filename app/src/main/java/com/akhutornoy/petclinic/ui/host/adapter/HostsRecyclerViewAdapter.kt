package com.akhutornoy.petclinic.ui.host.adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.akhutornoy.petclinic.R
import com.akhutornoy.petclinic.entity.ui.HostModel

import kotlinx.android.synthetic.main.item_host.view.*

class HostsRecyclerViewAdapter(
    private val listener: OnHostListInteractionListener?
) : RecyclerView.Adapter<HostsRecyclerViewAdapter.ViewHolder>() {

    var items: List<HostModel> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    private val onPreviewClickListener: View.OnClickListener
    private val onDeleteClickListener: View.OnClickListener

    init {
        onPreviewClickListener = View.OnClickListener { v ->
            val item = v.tag as HostModel
            listener?.onHostDetailsClicked(item)
        }

        onDeleteClickListener = View.OnClickListener { v ->
            val item = v.tag as HostModel
            listener?.onHostDeleteClicked(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_host, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.tvId.text = item.id.toString()
        holder.tvFirstName.text = item.firstName
        holder.tvLastName.text = item.lastName

        with(holder.view) {
            tag = item
            setOnClickListener(onPreviewClickListener)
        }

        with(holder.ivDelete) {
            tag = item
            setOnClickListener(onDeleteClickListener)
        }
    }

    override fun getItemCount(): Int = items.size

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val tvId: TextView = view.item_number
        val tvFirstName: TextView = view.tv_first_name
        val tvLastName: TextView = view.tv_last_name
        val ivDelete = view.iv_delete
    }

}

interface OnHostListInteractionListener {
    fun onHostDetailsClicked(item: HostModel)
    fun onHostDeleteClicked(item: HostModel)
}
