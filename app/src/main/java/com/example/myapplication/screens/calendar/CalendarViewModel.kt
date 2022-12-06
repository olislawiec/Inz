package com.example.myapplication.screens.calendar

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.myapplication.localdb.RecipeDao
import com.example.myapplication.localdb.RecipeItem
import com.example.myapplication.localdb.RecipeItemCalendar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class CalendarViewModel(
    private val dataSource: RecipeDao
) : ViewModel() {
    val recipes= dataSource.getallrecipesdates()
    fun deleterecipe(recipeItem: RecipeItemCalendar){
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                dataSource.delete(recipeItem)
            }

        }
    }
    fun updateRecipe(recipeItem: RecipeItemCalendar){
        viewModelScope.launch{
            withContext(Dispatchers.IO){
                dataSource.update(recipeItem)
            }
        }
    }
}
class RecipeCalendarViewModelFactory(
    private val dataSource: RecipeDao

): ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CalendarViewModel::class.java)) {
            return CalendarViewModel(dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}