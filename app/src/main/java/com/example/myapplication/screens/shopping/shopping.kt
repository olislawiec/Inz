package com.example.myapplication.screens.shopping

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
import com.example.myapplication.databinding.FragmentShoppingBinding
import com.example.myapplication.databinding.ShoppingitemBinding
import com.example.myapplication.localdb.RecipesDatabase
import com.example.myapplication.objects.Recipe
import com.example.myapplication.screens.recipebook.RecipeBookViewModelFactory
import com.example.myapplication.screens.recipebook.RecipebookViewModel

class shopping : Fragment() {

    companion object {
        fun newInstance() = shopping()
    }
    private lateinit var binding: FragmentShoppingBinding
    private lateinit var viewModel: ShoppingListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=DataBindingUtil.inflate(inflater,R.layout.fragment_shopping,container,false)
        val application = requireNotNull(this.activity).application
        val dataSource = RecipesDatabase.getInstance(application).recipesDatabaseDao
        val viewModelFactory = RecipeShoppingListViewModelFactory(
            dataSource
        )
        viewModel = ViewModelProvider(this, viewModelFactory).get(
            ShoppingListViewModel::class.java
        )
        val adapter = RecipeShoppingListAdapter(
            RecipeShoppingListListener {
                val recipe=Recipe()
                recipe.name=it.recipeName
                recipe.shoppinglist=it.recipeShoppingList
                recipe.przepis=it.recipeDetails
                recipe.image=it.recipeImageUrl
                findNavController().navigate(shoppingDirections.actionShoppingToRecipedetails(recipe,false))

            },
            RecipeShoppingListListenerDelete {
                viewModel
                    .deleterecipe(it)
            }
        )
        binding.shoppingList.adapter=adapter
        viewModel.recipes.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })
        binding.addownproducts.setOnClickListener {
            binding.addownproducts.visibility=View.INVISIBLE
            binding.buttonAddProduct.visibility=View.VISIBLE
            binding.textField1.visibility=View.VISIBLE
        }
        binding.buttonAddProduct.setOnClickListener {
            if (!binding.productName.text.toString().isNullOrEmpty()){
                viewModel.addProduct(binding.productName.text.toString())
                binding.addownproducts.visibility=View.VISIBLE
                binding.buttonAddProduct.visibility=View.INVISIBLE
                binding.textField1.visibility=View.INVISIBLE
            }
        }
        return binding.root
    }



}