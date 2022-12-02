package com.example.myapplication.screens.recipebook

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.IngredientBinding
import com.example.myapplication.objects.Ingredient


class IngredientsAdapter() : ListAdapter<Ingredient, ViewHolderIngredient>(
    IngredientDiffCallback()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderIngredient {
        return ViewHolderIngredient.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolderIngredient, position: Int) {
        holder.bind(getItem(position))
    }


}
class ViewHolderIngredient private constructor(val binding: IngredientBinding): RecyclerView.ViewHolder(binding.root) {
    fun bind(
        ingredient: Ingredient,

    ) {
        binding.ingredient = ingredient
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