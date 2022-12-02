package com.example.myapplication.localdb

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
    fun getallrecipes(): List<RecipeItem>
}