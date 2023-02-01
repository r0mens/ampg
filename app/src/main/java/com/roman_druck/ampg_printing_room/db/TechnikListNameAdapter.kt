package com.roman_druck.ampg_printing_room.db

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
//import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.roman_druck.ampg_printing_room.R
import com.roman_druck.ampg_printing_room.databinding.ListNameItemBinding
import com.roman_druck.ampg_printing_room.entities.TechnikState

class TechnikListNameAdapter(private val listener: Listener): ListAdapter<TechnikState, TechnikListNameAdapter.ItemHolder>(ItemComparator()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.setData(getItem(position), listener)
    }
    class ItemHolder(view:View) : RecyclerView.ViewHolder(view){
        private val binding = ListNameItemBinding.bind(view)
        fun setData(listName: TechnikState, listener: Listener) = with(binding){
            tvNameList.text = listName.name_list
            tvTimeList.text = listName.date

            itemView.setOnClickListener {
                listener.onClickItem(listName)
            }
            imDeleteNameList.setOnClickListener {
                listener.deleteItem(listName.id!!)
            }
            imUpdateNameList.setOnClickListener {
                listener.updateItem(listName)
            }



        }
        companion object{
            fun create(parent: ViewGroup): ItemHolder{
                return ItemHolder(
                             LayoutInflater.from(parent.context)
                            .inflate(R.layout.list_name_item, parent, false))
            }
        }
    }
    class ItemComparator : DiffUtil.ItemCallback<TechnikState>(){
        override fun areItemsTheSame(oldItem: TechnikState, newItem: TechnikState): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: TechnikState, newItem: TechnikState): Boolean {
            return oldItem == newItem
        }

    }
    interface Listener{
        fun deleteItem(id: Int)
        fun updateItem(name: TechnikState)
        fun onClickItem(listName: TechnikState)
    }
}


