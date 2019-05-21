package com.akhutornoy.petclinic.ui.pet.adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.akhutornoy.petclinic.R
import com.akhutornoy.petclinic.entity.ui.PetModel
import kotlinx.android.synthetic.main.item_pet.view.*

class PetsRecyclerViewAdapter(
    private val listener: OnPetInteractionListener?
) : RecyclerView.Adapter<PetsRecyclerViewAdapter.ViewHolder>() {

    var items: List<PetModel> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    private val onDeleteClickListener: View.OnClickListener

    init {
        onDeleteClickListener = View.OnClickListener { v ->
            val item = v.tag as PetModel
            listener?.onPetDeleteClicked(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_pet, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.tvId.text = item.id.toString()
        holder.tvName.text = item.name
        holder.tvBreed.text = item.breed

        with(holder.ivDelete) {
            tag = item
            setOnClickListener(onDeleteClickListener)
        }
    }

    override fun getItemCount(): Int = items.size

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val tvId: TextView = view.tv_id
        val tvName: TextView = view.tv_name
        val tvBreed: TextView = view.tv_breed
        val ivDelete = view.iv_delete
    }

}

interface OnPetInteractionListener {
    fun onPetDeleteClicked(item: PetModel)
}
