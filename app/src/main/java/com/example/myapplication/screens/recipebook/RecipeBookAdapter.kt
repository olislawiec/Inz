package com.example.myapplication.screens.recipebook


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.RecipeBinding
import com.example.myapplication.databinding.RecipeitemBinding
import com.example.myapplication.localdb.RecipeItem
import com.example.myapplication.objects.Recipe


class RecipeBookAdapter(private val clicklfordetailslistener: RecipeBookListener, private val clickforeditlistener: RecipeBookListenerEdit, private val clickforremovelistener: RecipeBookListenerDelete) : ListAdapter<RecipeItem, RecipeBookViewHolder>(
    RecipeItemDiffCallback()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeBookViewHolder {
        return RecipeBookViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: RecipeBookViewHolder, position: Int) {
        holder.bind(getItem(position), clicklfordetailslistener, clickforeditlistener, clickforremovelistener)
    }


}
class RecipeBookViewHolder private constructor(val binding: RecipeitemBinding): RecyclerView.ViewHolder(binding.root) {
    fun bind(
        recipe: RecipeItem,
        clicklfordetailslistener: RecipeBookListener,
        clickforeditlistener: RecipeBookListenerEdit,
        clickforremovelistener: RecipeBookListenerDelete


    ) {
        binding.recipe = recipe
        binding.name = recipe.recipeName
        binding.url = recipe.recipeImageUrl
        binding.clicklfordetailslistener = clicklfordetailslistener
        binding.clickforremovelistener = clickforremovelistener
        binding.clicklforeditslistener = clickforeditlistener
        binding.executePendingBindings()





    }
    companion object {
        fun from(parent: ViewGroup): RecipeBookViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = RecipeitemBinding.inflate(layoutInflater, parent, false)
            return RecipeBookViewHolder(binding)
        }
    }
}
class RecipeItemDiffCallback: DiffUtil.ItemCallback<RecipeItem>() {
    override fun areItemsTheSame(oldItem: RecipeItem, newItem: RecipeItem): Boolean {
        return false
    }

    override fun areContentsTheSame(oldItem: RecipeItem, newItem: RecipeItem): Boolean {
        return false
    }


}
class RecipeBookListener(val clickListener: (recipe: RecipeItem) -> Unit) {
    fun onClick(recipeitem: RecipeItem) = clickListener(recipeitem)
}
class RecipeBookListenerDelete(val clickListener: (recipe: RecipeItem) -> Unit) {
    fun onClick(recipeitem: RecipeItem) = clickListener(recipeitem)
}
class RecipeBookListenerEdit(val clickListener: (recipe: RecipeItem) -> Unit) {
    fun onClick(recipeitem: RecipeItem) = clickListener(recipeitem)
}
//class UsersListenerDelete(val clickListener: (user: wiktor.app.other.User) -> Unit) {
//    fun onClick(user: wiktor.app.other.User) = clickListener(user)
//}
//class UsersListenerEdit(val clickListener: (user: wiktor.app.other.User) -> Unit) {
//    fun onClick(user: wiktor.app.other.User) = clickListener(user)
//}