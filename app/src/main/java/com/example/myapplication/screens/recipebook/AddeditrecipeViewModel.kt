package com.example.myapplication.screens.recipebook

import android.accounts.AuthenticatorDescription
import androidx.lifecycle.*
import com.example.myapplication.localdb.RecipeDao
import com.example.myapplication.localdb.RecipeItem
import com.example.myapplication.objects.Ingredient
import com.example.myapplication.objects.Recipe
import com.example.myapplication.screens.recipedetails.recipedetails
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AddeditrecipeViewModel(private val dataSource: RecipeDao) : ViewModel() {
    val listUpdated = MutableLiveData<Boolean>()
    // TODO: Implement the ViewModel
    val shoppinglist= MutableLiveData<MutableList<Ingredient>>()
    init {
        shoppinglist.value = mutableListOf()
    }
    fun addingredientrecipe(ingredient: String){
        shoppinglist.value?.add(Ingredient(ingredient))
        listUpdated.value = true
    }
    fun createrecpefunction(name: String,description: String){
        viewModelScope.launch {
        createRecipe(name,description)
        }
    }
    suspend fun createRecipe(name: String,description: String){
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val recipeitem = RecipeItem()
                recipeitem.recipeName = name
                recipeitem.recipeDetails = description

                var shoppingliststring = ""
                for (x in shoppinglist.value?: mutableListOf()){
                    shoppingliststring+=x.name+"|"
                }
                recipeitem.recipeShoppingList = shoppingliststring
                dataSource.insert(recipeitem)
            }

        }



    }
}
class AddeditrecipeViewModelFactory(
    private val dataSource: RecipeDao

): ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddeditrecipeViewModel::class.java)) {
            return AddeditrecipeViewModel(dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}