package com.example.myapplication.screens.recipedetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentRecipedetailsBinding

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
        val list= recipe.shoppinglist.replace('#',' ').split('|')

        // access the listView from xml file
        var mListView = binding.shoppingList
        arrayAdapter = ArrayAdapter(requireContext(),
            android.R.layout.simple_list_item_1, list)
        mListView.adapter = arrayAdapter
        mListView.setClickable(false)
        justifyListViewHeightBasedOnChildren(mListView)
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