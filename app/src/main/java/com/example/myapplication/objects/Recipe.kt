package com.example.myapplication.objects

data class Recipe (
    var name:String = "",
    var image:String = "",
    var przepis:String = "",
    var shoppinglist:MutableList<String> = mutableListOf<String>()
)

