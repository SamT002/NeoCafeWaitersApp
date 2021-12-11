package com.example.neocafewaiterapplication.bottom_navigation.menu.all_product

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.neocafeteae1prototype.data.models.Resource
import com.example.neocafewaiterapplication.repository.Repository
import com.example.neocafewaiterapplication.tools.sealed_classes.AllModels
import com.example.neocafewaiterapplication.tools.sortByCategory
import kotlinx.coroutines.*

class AllProductViewModel(private val repository: Repository) : ViewModel() {
  //^^^^^^^^
    val list = MutableLiveData<Resource<MutableList<AllModels.Product>>>()

    init {
        getAllProduct()
    }

    private fun getAllProduct() {
        //viewModelScope используй
        //зачем так менять диспатчеры , вообще не целесообразно менять поток , для того чтобы вдругом потоке сделать запрос
        CoroutineScope(Dispatchers.IO).launch {
            val response =
                withContext(Dispatchers.Default) { repository.getAllProduct() }
            list.postValue(response)
        }
    }

 //сортировка по какому признаку?
    fun sort(
        category: String,
        list: MutableList<AllModels.Product>
    ): MutableList<AllModels.Product> {
        return list.sortByCategory(category)
    }

}