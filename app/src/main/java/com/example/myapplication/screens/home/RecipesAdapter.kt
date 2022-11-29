package com.example.myapplication.screens.home


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.RecipeBinding
import com.example.myapplication.objects.Recipe


class RecipesAdapter(private val clicklfordetailslistener: RecipeListener) : ListAdapter<Recipe, ViewHolder>(
    UsersDiffCallback()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), clicklfordetailslistener)
    }


}
class ViewHolder private constructor(val binding: RecipeBinding): RecyclerView.ViewHolder(binding.root) {
    fun bind(
        recipe: Recipe,
        clicklfordetailslistener: RecipeListener
    ) {
        binding.recipe = recipe
        binding.name = recipe.name
        binding.url = recipe.image
        binding.clicklfordetailslistener = clicklfordetailslistener
        binding.executePendingBindings()





    }
    companion object {
        fun from(parent: ViewGroup): ViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = RecipeBinding.inflate(layoutInflater, parent, false)
            return ViewHolder(binding)
        }
    }
}
class UsersDiffCallback: DiffUtil.ItemCallback<Recipe>() {
    override fun areItemsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
        return false
    }

    override fun areContentsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
        return false
    }


}
class RecipeListener(val clickListener: (recipe: Recipe) -> Unit) {
    fun onClick(recipe: Recipe) = clickListener(recipe)
}
//class UsersListenerDelete(val clickListener: (user: wiktor.app.other.User) -> Unit) {
//    fun onClick(user: wiktor.app.other.User) = clickListener(user)
//}
//class UsersListenerEdit(val clickListener: (user: wiktor.app.other.User) -> Unit) {
//    fun onClick(user: wiktor.app.other.User) = clickListener(user)
//}