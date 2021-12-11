package com.example.neocafewaiterapplication.bottom_navigation.menu

import androidx.lifecycle.ViewModel
import com.example.neocafewaiterapplication.R
import com.example.neocafewaiterapplication.tools.sealed_classes.AllModels

class MenuViewModel : ViewModel() {
    //название листа - лист
    //используй неизменяемые коллекции
    //используй константы
    private val list = mutableListOf<AllModels>(
        AllModels.Menu(R.drawable.ic_coffee, "Кофе"),
        AllModels.Menu(R.drawable.ic_bubble_tea, "Чай"),
        AllModels.Menu(R.drawable.ic_soda, "Напитки"),
        AllModels.Menu(R.drawable.ic_ice_cream, "Десерты"),
        AllModels.Menu(R.drawable.ic_pizza, "Выпечка"),
    )
        //имя функции getList))
    fun getList(): MutableList<AllModels> {
        return list
    }

}