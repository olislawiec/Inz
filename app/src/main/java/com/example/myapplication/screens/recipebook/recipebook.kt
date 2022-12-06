package com.example.myapplication.screens.recipebook

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.text.Layout.Directions
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentAddeditrecipeBinding
import com.example.myapplication.databinding.FragmentHomeBinding
import com.example.myapplication.databinding.FragmentRecipebookBinding
import com.example.myapplication.databinding.FragmentRecipedetailsBinding
import com.example.myapplication.localdb.RecipesDatabase
import com.example.myapplication.objects.Recipe
import com.example.myapplication.screens.home.RecipeListener
import com.example.myapplication.screens.home.RecipesAdapter
import com.example.myapplication.screens.recipedetails.recipedetails

class recipebook : Fragment() {

    companion object {
        fun newInstance() = recipebook()
    }
    private lateinit var binding: FragmentRecipebookBinding
    private lateinit var viewModel: RecipebookViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup ?,
        savedInstanceState: Bundle?
    ): View? {
        val application = requireNotNull(this.activity).application
        val dataSource = RecipesDatabase.getInstance(application).recipesDatabaseDao
        val viewModelFactory = RecipeBookViewModelFactory(
            dataSource
        )
        viewModel = ViewModelProvider(this, viewModelFactory).get(
            RecipebookViewModel::class.java
        )
        binding =
            DataBindingUtil.inflate<FragmentRecipebookBinding>(inflater, R.layout.fragment_recipebook,container,false)
        binding.button.setOnClickListener {
            findNavController().navigate(recipebookDirections.actionMyRecipesToAddeditrecipe(null))
        }
        val adapter = RecipeBookAdapter(RecipeBookListener {
            val recipe = Recipe()
            recipe.name=it.recipeName
            recipe.image=it.recipeImageUrl
            recipe.shoppinglist=it.recipeShoppingList
            recipe.przepis=it.recipeDetails
            findNavController().navigate(recipebookDirections.actionMyRecipesToRecipedetails(recipe,false))
        },
        RecipeBookListenerEdit {
            findNavController().navigate(recipebookDirections.actionMyRecipesToAddeditrecipe(it.recipeId.toString()))
        },
        RecipeBookListenerDelete
         {
             viewModel.deleterecipe(it)
         })
        binding.recipebookrecycler.adapter = adapter

        viewModel.recipes.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it.sortedBy { id }.reversed())
        })
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(RecipebookViewModel::class.java)
        // TODO: Use the ViewModel
    }

}