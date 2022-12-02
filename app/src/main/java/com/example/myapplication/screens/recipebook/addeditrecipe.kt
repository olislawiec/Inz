package com.example.myapplication.screens.recipebook

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentAddeditrecipeBinding
import com.example.myapplication.databinding.FragmentHomeBinding
import com.example.myapplication.databinding.FragmentRecipebookBinding
import com.example.myapplication.localdb.RecipesDatabase
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

        binding.viewModel = viewModel
        val adapter = IngredientsAdapter()
        binding.addingriedients.adapter = adapter
        /*viewModel.shoppinglist.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
            adapter.notifyDataSetChanged()

        })*/
        viewModel.listUpdated.observe(viewLifecycleOwner, Observer {
            if (it){
                adapter.submitList(viewModel.shoppinglist.value)
                viewModel.listUpdated.value = false
                adapter.notifyDataSetChanged()
                Log.d("test","${viewModel.shoppinglist.value?.size}")
            }
        })
        binding.imageButton.setOnClickListener {
            viewModel.addingredientrecipe(binding.ingredient.text.toString())
            binding.ingredient.setText("")
        }
        binding.buttonsubmit.setOnClickListener {
            viewModel.createrecpefunction(binding.recipeNameedit.text.toString(),binding.recipedetailsedit.text.toString())
        }
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AddeditrecipeViewModel::class.java)
        // TODO: Use the ViewModel
    }

}