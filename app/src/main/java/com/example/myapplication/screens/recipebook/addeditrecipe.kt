package com.example.myapplication.screens.recipebook

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentAddeditrecipeBinding
import com.example.myapplication.databinding.FragmentHomeBinding
import com.example.myapplication.databinding.FragmentRecipebookBinding
import com.example.myapplication.localdb.RecipesDatabase
import com.example.myapplication.objects.Ingredient
import kotlinx.android.synthetic.main.fragment_addeditrecipe.*
import kotlinx.android.synthetic.main.fragment_recipedetails.*


class addeditrecipe : Fragment() {

    companion object {
        fun newInstance() = addeditrecipe()
    }

    private lateinit var binding: FragmentAddeditrecipeBinding

    private lateinit var viewModel: AddeditrecipeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val application = requireNotNull(this.activity).application
        binding =
            DataBindingUtil.inflate<FragmentAddeditrecipeBinding>(inflater, R.layout.fragment_addeditrecipe,container,false)
        val dataSource = RecipesDatabase.getInstance(application).recipesDatabaseDao
        val viewModelFactory = AddeditrecipeViewModelFactory(
            dataSource
        )
        viewModel = ViewModelProvider(this, viewModelFactory).get(
            AddeditrecipeViewModel::class.java
        )

        val args= addeditrecipeArgs.fromBundle(requireArguments())
        if(!args.recipeid.isNullOrEmpty()){
            viewModel.getRecipe(args.recipeid?:"")
        }


        binding.viewModel = viewModel
        val adapter = IngredientsAdapter(IngredientListener {
                i ->
            Log.d("1","${viewModel.shoppinglist.value?.size}")
            viewModel.shoppinglist.value?.remove(i)
            Log.d("2","${viewModel.shoppinglist.value?.size}")
            viewModel.listUpdated.value = true
        })
        binding.addingriedients.adapter = adapter
        /*viewModel.shoppinglist.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
            adapter.notifyDataSetChanged()

        })*/

        viewModel.recipe.observe(viewLifecycleOwner, Observer {
            binding.recipeNameedit.setText(it.recipeName)
            binding.recipedetailsedit.setText(it.recipeDetails)
            viewModel.imgUrl = it.recipeImageUrl
            val ingredients = mutableListOf<Ingredient>()
            if(it.recipeShoppingList.length>0) {
                it.recipeShoppingList.replace('#', ' ').split('|').forEach {
                    val ingredient = Ingredient()
                    ingredient.name = it


                    ingredients.add(ingredient)

                }
                viewModel.shoppinglist.value=ingredients

                adapter.submitList(ingredients)
            }
            binding.buttonsubmit.setText("Zapisz")
        })
        viewModel.listUpdated.observe(viewLifecycleOwner, Observer {
            if (it){
                adapter.submitList(viewModel.shoppinglist.value)
                viewModel.listUpdated.value = false
                adapter.notifyDataSetChanged()
                Log.d("wielkosclisty","${viewModel.shoppinglist.value?.size}")
            }
        })

        binding.imageButton.setOnClickListener {
            if(!binding.ingredient.text.toString().isNullOrEmpty()){
                viewModel.addingredientrecipe(binding.ingredient.text.toString())
                binding.ingredient.setText("")
            }
            else{
                Toast.makeText(
                    requireActivity().application.applicationContext,
                    "Pole składnika nie może być puste!",
                    Toast.LENGTH_SHORT
                ).show()
            }

        }
        binding.buttonsubmit.setOnClickListener {
            if(binding.recipeNameedit.text.toString().isNullOrEmpty() || binding.recipedetailsedit.text.toString().isNullOrEmpty() || (viewModel.shoppinglist.value?: mutableListOf()).isEmpty()){

                Toast.makeText(
                    requireActivity().application.applicationContext,
                    "Nazwa, Szczegóły i lista składników nie mogą być puste!",
                    Toast.LENGTH_SHORT
                ).show()
        }
            else{
                if(!args.recipeid.isNullOrEmpty()){
                    viewModel.editRecipe(args.recipeid!!.toLong(),binding.recipeNameedit.text.toString(),binding.recipedetailsedit.text.toString())
                    findNavController().popBackStack()
                }
                else{
                    viewModel.createrecpefunction(binding.recipeNameedit.text.toString(),binding.recipedetailsedit.text.toString())
                    findNavController().popBackStack()
                }
            }
        }
        return binding.root

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AddeditrecipeViewModel::class.java)
        // TODO: Use the ViewModel
    }
    public fun String.dropLast(n: Int): String {
        require(n >= 0) { "Requested character count $n is less than zero." }
        return take((length - n).coerceAtLeast(0))
    }

}