package com.example.neocafewaiterapplication.bottom_navigation.menu.all_product

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.neocafewaiterapplication.tools.sealed_classes.AllModels

class AllProductViewModel : ViewModel() {

    private var sortedList = mutableListOf<AllModels.Product>()

    private val productList = listOf<AllModels.Product>(
        AllModels.Product("Латте", "120c", "Кофе"),
        AllModels.Product("Капучино", "120c","Кофе"),
        AllModels.Product("Coca-Cola", "35c","Напитки"),
        AllModels.Product("Чизкейк", "100c", "Десерты"),
        AllModels.Product("Пирог", "70c", "Выпечка"),
    )

    private val list: MutableLiveData<List<AllModels.Product>> = MutableLiveData(productList)


    fun getList() = list
    fun getSortedList() = sortedList

    fun sort(category:String, list:MutableList<AllModels.Product>):MutableList<AllModels.Product>{
        val myList = mutableListOf<AllModels.Product>()
        for (i in list){
            if (i.category == category){
                myList.add(i)
            }

        }
        sortedList = myList
        if(category == "Все"){
            return list
        }

        return sortedList
    }

}