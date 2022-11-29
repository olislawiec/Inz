package com.example.myapplication.screens.recipedetails

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentHomeBinding
import com.example.myapplication.databinding.FragmentRecipedetailsBinding
import com.example.myapplication.databinding.RecipeBinding

class recipedetails : Fragment() {

    companion object {
        fun newInstance() = recipedetails()
    }

    private lateinit var viewModel: RecipedetailsViewModel
    private lateinit var binding: FragmentRecipedetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val args = recipedetailsArgs.fromBundle(requireArguments())
        val recipe = args.recipe
        binding = DataBindingUtil.inflate<FragmentRecipedetailsBinding>(inflater, R.layout.fragment_recipedetails,container,false)

        binding.recipedetails = recipe
        val arrayAdapter: ArrayAdapter<*>
        val users = arrayOf(
            "Virat Kohli", "Rohit Sharma", "Steve Smith",
            "Kane Williamson", "Ross Taylor"
        )

        // access the listView from xml file
        var mListView = binding.shoppingList
        arrayAdapter = ArrayAdapter(requireContext(),
            android.R.layout.simple_list_item_1, recipe.shoppinglist)
        mListView.adapter = arrayAdapter
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(RecipedetailsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}