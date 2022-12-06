package com.example.myapplication.screens.recipebook

import android.accounts.AuthenticatorDescription
import android.util.Log
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

    val recipe=MutableLiveData<RecipeItem>()
    fun getRecipe(id: String){


        viewModelScope.launch {
            var recipeitem= RecipeItem()
            withContext(Dispatchers.IO){
                recipeitem=dataSource.getProduct(id.toLong())?: RecipeItem()
            }
            recipe.value=recipeitem
        }
    }
    var imgUrl = "https://firebasestorage.googleapis.com/v0/b/asystentgotowania.appspot.com/o/notfound.png?alt=media&token=4847c5f0-bb50-41f1-9d10-54e18e375bd3"
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
                var length=0
                for (x in shoppinglist.value?: mutableListOf()){
                    length+=1
                    shoppingliststring+=x.name+"|"
                }


                Log.d("przed", shoppingliststring)



                recipeitem.recipeShoppingList = shoppingliststring.dropLast(1)
                Log.d("po", recipeitem.recipeShoppingList)
                dataSource.insert(recipeitem)
            }

        }
    }
    fun editRecipe(id: Long,name: String,description: String){

        val cc = shoppinglist.value?.size?:""
        Log.d("testwiktorkapoteznego1", cc.toString())
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val recipeitem = dataSource.getProduct(id)
                recipeitem?.recipeName = name
                recipeitem?.recipeDetails = description

                var shoppingliststring = ""
                for (x in shoppinglist.value?: mutableListOf()){

                    shoppingliststring+=x.name+"|"
                }
                Log.d("po", shoppingliststring)

                recipeitem?.recipeShoppingList = shoppingliststring.dropLast(1)
                recipeitem?.recipeImageUrl = imgUrl
                recipeitem.let {
                    dataSource.update(recipeitem!!)
                }
                Log.d("testwiktorkapoteznego||", cc.toString())


            }


        }

    }
    public fun String.dropLast(n: Int): String {
        require(n >= 0) { "Requested character count $n is less than zero." }
        return take((length - n).coerceAtLeast(0))
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