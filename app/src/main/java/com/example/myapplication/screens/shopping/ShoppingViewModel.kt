package com.example.myapplication.screens.shopping

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.myapplication.localdb.RecipeDao
import com.example.myapplication.localdb.RecipeItem
import com.example.myapplication.localdb.RecipeItemShoppingList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ShoppingListViewModel(private val dataSource: RecipeDao) : ViewModel() {
    val recipes= dataSource.getshoppinglist()
    fun deleterecipe(recipeItem: RecipeItemShoppingList){
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                dataSource.delete(recipeItem)
            }

        }
    }
    fun addProduct(productname: String){
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val recipe = RecipeItemShoppingList()
                recipe.recipeShoppingList = productname
                dataSource.insert(recipe)
            }
        }

    }
}
class RecipeShoppingListViewModelFactory(
    private val dataSource: RecipeDao

): ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ShoppingListViewModel::class.java)) {
            return ShoppingListViewModel(dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}