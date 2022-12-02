package com.example.myapplication.localdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [RecipeItem::class], version = 2, exportSchema = false)
abstract class RecipesDatabase: RoomDatabase() {
    abstract val recipesDatabaseDao: RecipeDao
    companion object {
        @Volatile
        private var INSTANCE: RecipesDatabase? = null
        fun getInstance(context: Context): RecipesDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        RecipesDatabase::class.java,
                        "recipes_database_history").fallbackToDestructiveMigration().build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}