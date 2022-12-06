package com.example.myapplication.screens.recipebook

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.myapplication.localdb.RecipeDao
import com.example.myapplication.localdb.RecipeItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RecipebookViewModel(private val dataSource: RecipeDao) : ViewModel() {
    val recipes= dataSource.getallrecipes()
    fun deleterecipe(recipeItem: RecipeItem){
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                dataSource.delete(recipeItem)
            }

        }
    }
}
class RecipeBookViewModelFactory(
    private val dataSource: RecipeDao

): ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RecipebookViewModel::class.java)) {
            return RecipebookViewModel(dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}