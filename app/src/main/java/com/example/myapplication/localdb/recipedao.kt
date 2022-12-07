package com.example.myapplication.localdb

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert

@Dao
interface RecipeDao {
    @Insert
    fun insert(recipe: RecipeItem)
    @Delete
    fun delete(recipe: RecipeItem)
    @Update
    fun update(recipe:RecipeItem)
    @Query( "SELECT * from recipebook")
    fun getallrecipes(): LiveData<List<RecipeItem>>
    @Query("SELECT * from recipebook where recipeId==:id")
    fun getProduct(id: Long): RecipeItem?



    @Insert
    fun insert(recipe: RecipeItemCalendar)
    @Delete
    fun delete(recipe: RecipeItemCalendar)
    @Query( "SELECT * from recipecalendar")
    fun getallrecipesdates(): LiveData<List<RecipeItemCalendar>>
    @Update
    fun update(recipe:RecipeItemCalendar)


    @Insert
    fun insert(recipe: RecipeItemShoppingList)
    @Delete
    fun delete(recipe: RecipeItemShoppingList)
    @Query( "SELECT * from recipeshopinglist")
    fun getshoppinglist(): LiveData<List<RecipeItemShoppingList>>


}