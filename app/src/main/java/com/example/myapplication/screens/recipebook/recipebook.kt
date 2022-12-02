package com.example.myapplication.screens.recipebook

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.text.Layout.Directions
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentAddeditrecipeBinding
import com.example.myapplication.databinding.FragmentHomeBinding
import com.example.myapplication.databinding.FragmentRecipebookBinding
import com.example.myapplication.databinding.FragmentRecipedetailsBinding
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
        binding =
            DataBindingUtil.inflate<FragmentRecipebookBinding>(inflater, R.layout.fragment_recipebook,container,false)
        binding.button.setOnClickListener {
            findNavController().navigate(recipebookDirections.actionMyRecipesToAddeditrecipe(null))
        }
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(RecipebookViewModel::class.java)
        // TODO: Use the ViewModel
    }

}