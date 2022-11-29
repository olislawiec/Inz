package com.example.myapplication.screens.home

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.text.Layout
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentHomeBinding
import com.example.myapplication.objects.Recipe
import com.google.firebase.firestore.FirebaseFirestore

class home : Fragment() {

    companion object {
        fun newInstance() = home()
    }

    private lateinit var viewModel: HomeViewModel
    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapter: RecipesAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
         binding =
            DataBindingUtil.inflate<FragmentHomeBinding>(inflater, R.layout.fragment_home,container,false)


        adapter = RecipesAdapter(RecipeListener {recipe ->  findNavController().navigate(homeDirections.actionHomeToRecipedetails2(recipe)) } )
        binding.recipesList.adapter = adapter

        val db = FirebaseFirestore.getInstance()

        db.collection("Recipes")
            //.whereEqualTo("capital", true)
            .get()
            .addOnSuccessListener { documents ->
                val recipes = mutableListOf<Recipe>()
                for (document in documents) {

                    val recipe = document.toObject(Recipe::class.java)
                    recipe.let { recipes.add(it!!)  }



                    Log.d("KUPA2222", "${document.id} => ${document.data}")
                    Log.d("Lisya", recipes.toString())
                }
                adapter.submitList(recipes)
            }
            .addOnFailureListener { exception ->
                Log.w("KUPA3", "Error getting documents: ", exception)
            }

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        // TODO: Use the ViewModel
        //
    }

}