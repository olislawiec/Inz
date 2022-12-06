package com.example.myapplication.screens.calendar

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentCalendarBinding
import com.example.myapplication.databinding.FragmentRecipebookBinding
import com.example.myapplication.localdb.RecipeItemCalendar
import com.example.myapplication.localdb.RecipesDatabase
import com.example.myapplication.objects.Recipe
import com.example.myapplication.screens.recipebook.RecipeBookViewModelFactory
import com.example.myapplication.screens.recipebook.RecipebookViewModel
import com.example.myapplication.screens.recipebook.recipebook
import com.google.android.material.datepicker.MaterialDatePicker
import kotlinx.android.synthetic.main.recipeitemcalendar.*

class calendar : Fragment() {

    companion object {
        fun newInstance() = calendar()
    }

    private lateinit var viewModel: CalendarViewModel
    private lateinit var binding: FragmentCalendarBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val application = requireNotNull(this.activity).application
        val dataSource = RecipesDatabase.getInstance(application).recipesDatabaseDao
        val viewModelFactory = RecipeCalendarViewModelFactory(
            dataSource
        )
        viewModel = ViewModelProvider(this, viewModelFactory).get(
            CalendarViewModel::class.java
        )
        binding =
            DataBindingUtil.inflate<FragmentCalendarBinding>(inflater, R.layout.fragment_calendar,container,false)


        val adapter = RecipeBookAdapter(RecipeCalendarListener {
            val recipe = Recipe()
            recipe.name=it.recipeName
            recipe.image=it.recipeImageUrl
            recipe.shoppinglist=it.recipeShoppingList
            recipe.przepis=it.recipeDetails
            findNavController().navigate(calendarDirections.actionCalendarToRecipedetails(recipe,false))
        },
            RecipeCalendarListenerEdit {
                recipe ->
                val datePicker = MaterialDatePicker.Builder.datePicker()
                    .setTitleText("Wybierz date")
                    .setSelection(recipe.recipedate.toLong())
                    .build()

                datePicker.show(requireActivity().supportFragmentManager,"data")
                datePicker.addOnPositiveButtonClickListener {
                    recipe.recipedate = it.toString()

                    viewModel.updateRecipe(recipe)


                }

            },
            RecipeCalendarListenerDelete
            {
                viewModel.deleterecipe(it)
            })
        binding.recyclerviewcal.adapter = adapter

        viewModel.recipes.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it.sortedBy { recipeItemCalendar -> recipeItemCalendar.recipedate  })
        })


        return binding.root

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CalendarViewModel::class.java)
        // TODO: Use the ViewModel
    }

}