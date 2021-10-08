package com.example.neocafewaiterapplication.tools.recycler_adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.neocafewaiterapplication.R
import com.example.neocafewaiterapplication.databinding.MenuItemBinding
import com.example.neocafewaiterapplication.databinding.NotificationItemBinding
import com.example.neocafewaiterapplication.databinding.ProductItemBinding
import com.example.neocafewaiterapplication.tools.delegates.RecyclerItemClick
import com.example.neocafewaiterapplication.tools.sealed_classes.AllModels
import com.example.neocafewaiterapplication.tools.sealed_classes.AllViewHolders

class MainRecyclerViewAdapter(val click:RecyclerItemClick?) : RecyclerView.Adapter<AllViewHolders>() {

    private var list = mutableListOf<AllModels>()

    fun setList(list:MutableList<AllModels>){
        this.list = list
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllViewHolders {
        val inflater =  LayoutInflater.from(parent.context)
        return when(viewType){
            R.layout.menu_item -> {AllViewHolders.MenuViewHolder(MenuItemBinding.inflate(inflater
                , parent, false))}

            R.layout.notification_item -> {AllViewHolders.NotificationViewHolder(
                NotificationItemBinding.inflate(inflater, parent, false))}

            else -> throw IllegalArgumentException("Invalid Type from adapter")
        }
    }

    override fun onBindViewHolder(holder: AllViewHolders, position: Int) {
        val model = list[position]
        return when(holder) {
            is AllViewHolders.MenuViewHolder -> {
                holder.itemView.setOnClickListener { click?.clickListener(model) }
                holder.bind(model as AllModels.Menu)
            }

            is AllViewHolders.NotificationViewHolder -> {holder.bind(model as AllModels.Notification)}
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun getItemViewType(position: Int): Int {
        return when(list[position]){
            is AllModels.Menu -> R.layout.menu_item
            is AllModels.Product -> super.getItemViewType(position)
            is AllModels.Notification -> R.layout.notification_item
        }
    }
}