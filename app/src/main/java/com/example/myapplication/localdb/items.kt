package com.example.myapplication.localdb

import android.media.Image
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.myapplication.screens.recipedetails.recipedetails

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
    var recipeImageUrl: String = "http://www.tea-tron.com/antorodriguez/blog/wp-content/uploads/2016/04/image-not-found-4a963b95bf081c3ea02923dceaeb3f8085e1a654fc54840aac61a57a60903fef.png")