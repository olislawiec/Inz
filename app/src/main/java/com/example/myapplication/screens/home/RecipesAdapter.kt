package com.example.myapplication.screens.home


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.RecipeBinding
import com.example.myapplication.objects.Recipe


class RecipesAdapter(

) : ListAdapter<Recipe, ViewHolder>(
    UsersDiffCallback()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


}
class ViewHolder private constructor(val binding: RecipeBinding): RecyclerView.ViewHolder(binding.root) {
    fun bind(
        user: Recipe
    ) {
        binding.name = user.name
        binding.url = user.image

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
//class UsersListenerDelete(val clickListener: (user: wiktor.app.other.User) -> Unit) {
//    fun onClick(user: wiktor.app.other.User) = clickListener(user)
//}
//class UsersListenerEdit(val clickListener: (user: wiktor.app.other.User) -> Unit) {
//    fun onClick(user: wiktor.app.other.User) = clickListener(user)
//}