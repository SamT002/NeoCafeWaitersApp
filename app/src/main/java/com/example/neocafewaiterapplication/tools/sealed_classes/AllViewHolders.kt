package com.example.neocafewaiterapplication.tools.sealed_classes

import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.neocafewaiterapplication.databinding.MenuItemBinding
import com.example.neocafewaiterapplication.databinding.NotificationItemBinding
import com.example.neocafewaiterapplication.databinding.ProductItemBinding
import com.example.neocafewaiterapplication.tools.Consts

sealed class AllViewHolders(binding:ViewBinding) : RecyclerView.ViewHolder(binding.root){

    class MenuViewHolder(val binding:MenuItemBinding) : AllViewHolders(binding){
        fun bind(item:AllModels.Menu){
            with(binding){
                menuText.text = item.category
                menuIcon.setImageResource(item.icon)
            }

        }
    }

    class NotificationViewHolder(val binding:NotificationItemBinding) : AllViewHolders(binding){

        fun bind(item:AllModels.Notification){
            val color = when (item.status){
                "Заказ готов" -> Consts.GREEN
                "Бариста принял заказ" -> Consts.GREEN
                else -> Consts.WHITE
            }

           with(binding){
               status.setTextColor(ContextCompat.getColor(status.context, color))
               status.text = item.status
               time.text = item.time
               receipt.text = item.products


           }

        }

    }

}
