package com.akhutornoy.petclinic.ui.host.adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.akhutornoy.petclinic.R
import com.akhutornoy.petclinic.entity.ui.HostModel

import com.akhutornoy.petclinic.ui.host.HostsFragment.OnHostListInteractionListener

import kotlinx.android.synthetic.main.item_hosts.view.*

class HostsRecyclerViewAdapter(
    private val listener: OnHostListInteractionListener?
) : RecyclerView.Adapter<HostsRecyclerViewAdapter.ViewHolder>() {

    var items: List<HostModel> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    private val onClickListener: View.OnClickListener

    init {
        onClickListener = View.OnClickListener { v ->
            val item = v.tag as HostModel
            // Notify the active callbacks interface (the activity, if the fragment is attached to
            // one) that an item has been selected.
            listener?.onHostInteraction(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_hosts, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.mIdView.text = item.id.toString()
        holder.mContentView.text = "${item.firstName} ${item.lastName}"

        with(holder.mView) {
            tag = item
            setOnClickListener(onClickListener)
        }
    }

    override fun getItemCount(): Int = items.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val mIdView: TextView = mView.item_number
        val mContentView: TextView = mView.content

        override fun toString(): String {
            return super.toString() + " '" + mContentView.text + "'"
        }
    }

}
