package com.example.myapplication.screens.search

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentHomeBinding
import com.example.myapplication.databinding.FragmentSearchBinding
import com.example.myapplication.objects.Recipe
import com.example.myapplication.screens.home.HomeViewModel
import com.example.myapplication.screens.home.RecipeListener
import com.example.myapplication.screens.home.RecipesAdapter
import com.example.myapplication.screens.home.homeDirections
import com.google.firebase.firestore.FirebaseFirestore

class search : Fragment() {

    companion object {
        fun newInstance() = search()
    }

    private lateinit var db: FirebaseFirestore
    private lateinit var binding: FragmentSearchBinding
    private lateinit var adapter: RecipesAdapter
    private lateinit var viewModel: SearchViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {binding =
        DataBindingUtil.inflate<FragmentSearchBinding>(inflater, R.layout.fragment_search,container,false)


        adapter = RecipesAdapter(RecipeListener { recipe ->
            findNavController().navigate(
            searchDirections.actionSearchToRecipedetails(recipe, true))
        } )
        binding.searchedItems.adapter = adapter

        db = FirebaseFirestore.getInstance()
        binding.searchButton.setOnClickListener {
            db.collection("Recipes")
                //.whereArrayContains("name", binding.searchText.text)
                .get()
                .addOnSuccessListener { documents ->
                    var recipes = mutableListOf<Recipe>()
                    for (document in documents) {

                        val recipe = document.toObject(Recipe::class.java)
                        recipe.let { recipes.add(it!!) }



                        Log.d("KUPAsearch", "${document.id} => ${document.data}")
                        Log.d("Lisyasearch", recipes.toString())
                    }
                    recipes = recipes.filter { binding.searchText.text in it.name }.toMutableList()
                    adapter.submitList(recipes)
                }
                .addOnFailureListener { exception ->
                    Log.w("KUPA3search", "Error getting documents: ", exception)
                }
        }
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        if (!binding.searchText.text.toString().isNullOrEmpty()){
            db.collection("Recipes")
                //.whereArrayContains("name", binding.searchText.text)
                .get()
                .addOnSuccessListener { documents ->
                    var recipes = mutableListOf<Recipe>()
                    for (document in documents) {

                        val recipe = document.toObject(Recipe::class.java)
                        recipe.let { recipes.add(it!!) }



                        Log.d("KUPAsearch", "${document.id} => ${document.data}")
                        Log.d("Lisyasearch", recipes.toString())
                    }
                    recipes = recipes.filter { binding.searchText.text in it.name }.toMutableList()
                    adapter.submitList(recipes)
                }
                .addOnFailureListener { exception ->
                    Log.w("KUPA3search", "Error getting documents: ", exception)
                }

        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SearchViewModel::class.java)
        // TODO: Use the ViewModel
    }

}