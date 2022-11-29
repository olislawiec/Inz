package com.example.myapplication.objects

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Recipe (
    var id:String = "",
    var name:String = "",
    var image:String = "",
    var przepis:String = "",
    var shoppinglist:MutableList<String> = mutableListOf<String>()
) : Parcelable

