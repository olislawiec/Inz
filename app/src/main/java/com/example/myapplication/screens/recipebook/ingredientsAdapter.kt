package com.example.myapplication.screens.recipebook

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.IngredientBinding
import com.example.myapplication.objects.Ingredient
import com.example.myapplication.objects.Recipe


class IngredientsAdapter(private val listener: IngredientListener) : ListAdapter<Ingredient, ViewHolderIngredient>(
    IngredientDiffCallback()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderIngredient {
        return ViewHolderIngredient.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolderIngredient, position: Int) {
        holder.bind(getItem(position), listener)
    }


}
class ViewHolderIngredient private constructor(val binding: IngredientBinding): RecyclerView.ViewHolder(binding.root) {
    fun bind(
        ingredient: Ingredient,
        listener: IngredientListener

    ) {
        binding.ingredient = ingredient
        binding.listener = listener
        binding.executePendingBindings()





    }
    companion object {
        fun from(parent: ViewGroup): ViewHolderIngredient {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = IngredientBinding.inflate(layoutInflater, parent, false)
            return ViewHolderIngredient(binding)
        }
    }
}
class IngredientDiffCallback: DiffUtil.ItemCallback<Ingredient>() {
    override fun areItemsTheSame(oldItem: Ingredient, newItem: Ingredient): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Ingredient, newItem: Ingredient): Boolean {
        return oldItem == newItem
    }



}
class IngredientListener(val clickListener: (ingredient: Ingredient) -> Unit) {
    fun onClick(ingredient: Ingredient) = clickListener(ingredient)
}