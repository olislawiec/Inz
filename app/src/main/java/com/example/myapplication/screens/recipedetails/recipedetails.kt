package com.example.myapplication.screens.recipedetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentRecipedetailsBinding
import com.example.myapplication.localdb.RecipesDatabase
import com.example.myapplication.screens.recipebook.AddeditrecipeViewModel
import com.example.myapplication.screens.recipebook.AddeditrecipeViewModelFactory
import com.google.android.material.datepicker.MaterialDatePicker
import java.text.SimpleDateFormat
import java.util.*

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
        val application = requireNotNull(this.activity).application
        val args = recipedetailsArgs.fromBundle(requireArguments())
        val recipe = args.recipe
        binding = DataBindingUtil.inflate<FragmentRecipedetailsBinding>(inflater, R.layout.fragment_recipedetails,container,false)

        binding.recipedetails = recipe
        val arrayAdapter: ArrayAdapter<*>
        val list= recipe.shoppinglist.replace('#',' ').split('|')


        val dataSource = RecipesDatabase.getInstance(application).recipesDatabaseDao
        val viewModelFactory = RecipeDetailsViewModelFactory(
            dataSource
        )
        viewModel = ViewModelProvider(this, viewModelFactory).get(
            RecipedetailsViewModel::class.java
        )

        // access the listView from xml file
        var mListView = binding.shoppingList
        arrayAdapter = ArrayAdapter(requireContext(),
            android.R.layout.simple_list_item_1, list)
        mListView.adapter = arrayAdapter
        mListView.setClickable(false)
        justifyListViewHeightBasedOnChildren(mListView)
        if(!args.showAddtomyrecipes){
            binding.addToMyRecipes.visibility=View.GONE
        }
        else{
            binding.addToMyRecipes.visibility=View.VISIBLE
        }
        binding.addToMyRecipes.setOnClickListener { viewModel.addRecipeToMyRecipes(args.recipe) }
        binding.addToShoppinglist.setOnClickListener {
            viewModel.addRecipeToShoppingList(args.recipe)
        }
        binding.addToCalendar.setOnClickListener {

            val datePicker = MaterialDatePicker.Builder.datePicker()
                .setTitleText("Wybierz date")
                .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                .build()

            datePicker.show(requireActivity().supportFragmentManager,"data")
            datePicker.addOnPositiveButtonClickListener {

                viewModel.addRecipeToCalendar(args.recipe,it.toString())


            }
        }
        viewModel.updated.observe(viewLifecycleOwner, Observer {
            if (it){
                findNavController().popBackStack()
                viewModel.updated.value=false

            }
        })
        viewModel.updatedcal.observe(viewLifecycleOwner, Observer {
            if (it){
                findNavController().popBackStack()
                viewModel.updatedcal.value=false

            }
        })

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(RecipedetailsViewModel::class.java)
        // TODO: Use the ViewModel
    }
    fun justifyListViewHeightBasedOnChildren(listView: ListView) {
        val adapter = listView.adapter ?: return
        val vg: ViewGroup = listView
        var totalHeight = 0
        for (i in 0 until adapter.count) {
            val listItem = adapter.getView(i, null, vg)
            listItem.measure(0, 0)
            totalHeight += listItem.measuredHeight
        }
        val par = listView.layoutParams
        par.height = totalHeight + listView.dividerHeight * (adapter.count - 1)
        listView.layoutParams = par
        listView.requestLayout()
    }

}