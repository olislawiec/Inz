package com.example.myapplication.localdb

import android.media.Image
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.myapplication.screens.recipedetails.recipedetails
import com.google.type.DateTime
import java.util.*


@Entity(tableName = "recipebook")
data class RecipeItem(
    @PrimaryKey(autoGenerate = true)
    var recipeId: Long = 0L,
    @ColumnInfo(name = "recipe_name")
    var recipeName: String = "",
    @ColumnInfo(name = "recipe_shopping_list")
    var recipeShoppingList: String = "",
    @ColumnInfo(name = "recipe_details")
    var recipeDetails: String = "",
    @ColumnInfo(name = "recipe_imageurl")
    var recipeImageUrl: String = "https://firebasestorage.googleapis.com/v0/b/asystentgotowania.appspot.com/o/notfound.png?alt=media&token=4847c5f0-bb50-41f1-9d10-54e18e375bd3")


@Entity(tableName = "recipecalendar")
data class RecipeItemCalendar(
    @PrimaryKey(autoGenerate = true)
    var recipeId: Long = 0L,
    @ColumnInfo(name = "recipe_namecal")
    var recipeName: String = "",
    @ColumnInfo(name = "recipe_datecal")
    var recipedate: String="" ,
    @ColumnInfo(name = "recipe_shopping_listcal")
    var recipeShoppingList: String = "",
    @ColumnInfo(name = "recipe_detailscal")
    var recipeDetails: String = "",
    @ColumnInfo(name = "recipe_imageurlcal")
    var recipeImageUrl: String = "https://firebasestorage.googleapis.com/v0/b/asystentgotowania.appspot.com/o/notfound.png?alt=media&token=4847c5f0-bb50-41f1-9d10-54e18e375bd3")


@Entity(tableName = "recipeshopinglist")
data class RecipeItemShoppingList(
    @PrimaryKey(autoGenerate = true)
    var recipeId: Long = 0L,
    @ColumnInfo(name = "recipe_nameshop")
    var recipeName: String = "",
    @ColumnInfo(name = "recipe_dateshop")
    var recipedate: String="" ,
    @ColumnInfo(name = "recipe_shopping_listshop")
    var recipeShoppingList: String = "",
    @ColumnInfo(name = "recipe_detailsshop")
    var recipeDetails: String = "",
    @ColumnInfo(name = "recipe_imageurlshop")
    var recipeImageUrl: String = "https://firebasestorage.googleapis.com/v0/b/asystentgotowania.appspot.com/o/notfound.png?alt=media&token=4847c5f0-bb50-41f1-9d10-54e18e375bd3")