package com.example.neocafewaiterapplication.tools.sealed_classes

sealed class AllModels {

    data class Menu(val icon:Int, val category:String): AllModels()

    data class Product(val name:String, val price:String, val category: String): AllModels()

    data class Notification (val status:String, val products:String, val time:String): AllModels()

}