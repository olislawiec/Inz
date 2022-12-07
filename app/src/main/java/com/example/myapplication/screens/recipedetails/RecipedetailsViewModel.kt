package com.example.myapplication.screens.recipedetails

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.myapplication.localdb.RecipeDao
import com.example.myapplication.localdb.RecipeItem
import com.example.myapplication.localdb.RecipeItemCalendar
import com.example.myapplication.localdb.RecipeItemShoppingList
import com.example.myapplication.objects.Recipe
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class RecipedetailsViewModel(
    private val dataSource: RecipeDao

) : ViewModel() {

    val updated=MutableLiveData<Boolean>()
    val updatedcal=MutableLiveData<Boolean>()

    fun addRecipeToMyRecipes(recipe: Recipe){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                val recipedb = RecipeItem()
                recipedb.recipeName=recipe.name
                recipedb.recipeDetails=recipe.przepis
                recipedb.recipeShoppingList=recipe.shoppinglist
                recipedb.recipeImageUrl=recipe.image
                dataSource.insert(recipedb)

            }
            updated.value=true
        }

    }
    fun addRecipeToCalendar(recipe: Recipe,date:String){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                val recipedb = RecipeItemCalendar()
                recipedb.recipeName=recipe.name
                recipedb.recipeDetails=recipe.przepis
                recipedb.recipeShoppingList=recipe.shoppinglist
                recipedb.recipeImageUrl=recipe.image
                recipedb.recipedate=date
                dataSource.insert(recipedb)

            }
            updatedcal.value=true
        }

    }

    fun addRecipeToShoppingList(recipe: Recipe){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                val recipedb = RecipeItemShoppingList()
                recipedb.recipeName=recipe.name
                recipedb.recipeDetails=recipe.przepis
                recipedb.recipeShoppingList=recipe.shoppinglist
                recipedb.recipeImageUrl=recipe.image

                dataSource.insert(recipedb)

            }
            updatedcal.value=true
        }

    }

}
class RecipeDetailsViewModelFactory(
    private val dataSource: RecipeDao

): ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RecipedetailsViewModel::class.java)) {
            return RecipedetailsViewModel(dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}