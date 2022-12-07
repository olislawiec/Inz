package com.example.myapplication.screens.shopping


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.RecipeBinding
import com.example.myapplication.databinding.RecipeitemBinding
import com.example.myapplication.databinding.ShoppingitemBinding
import com.example.myapplication.localdb.RecipeItem
import com.example.myapplication.localdb.RecipeItemShoppingList
import com.example.myapplication.objects.Recipe


class RecipeShoppingListAdapter(private val clicklfordetailslistener: RecipeShoppingListListener, private val clickforremovelistener: RecipeShoppingListListenerDelete) : ListAdapter<RecipeItemShoppingList, RecipeShoppingListViewHolder>(
    RecipeShoppingItemDiffCallback()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeShoppingListViewHolder {
        return RecipeShoppingListViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: RecipeShoppingListViewHolder, position: Int) {
        holder.bind(getItem(position), clicklfordetailslistener, clickforremovelistener)
    }


}
class RecipeShoppingListViewHolder private constructor(val binding: ShoppingitemBinding): RecyclerView.ViewHolder(binding.root) {
    fun bind(
        recipe: RecipeItemShoppingList,
        clicklfordetailslistener: RecipeShoppingListListener,
        clickforremovelistener: RecipeShoppingListListenerDelete


    ) {
        binding.recipe = recipe

        binding.clicklfordetailslistener = clicklfordetailslistener
        binding.clickforremovelistener = clickforremovelistener

        binding.executePendingBindings()





    }
    companion object {
        fun from(parent: ViewGroup): RecipeShoppingListViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ShoppingitemBinding.inflate(layoutInflater, parent, false)
            return RecipeShoppingListViewHolder(binding)
        }
    }
}
class RecipeShoppingItemDiffCallback: DiffUtil.ItemCallback<RecipeItemShoppingList>() {
    override fun areItemsTheSame(oldItem: RecipeItemShoppingList, newItem: RecipeItemShoppingList): Boolean {
        return false
    }

    override fun areContentsTheSame(oldItem: RecipeItemShoppingList, newItem: RecipeItemShoppingList): Boolean {
        return false
    }


}
class RecipeShoppingListListener(val clickListener: (recipe: RecipeItemShoppingList) -> Unit) {
    fun onClick(recipeitemshoppinglist: RecipeItemShoppingList) = clickListener(recipeitemshoppinglist)
}
class RecipeShoppingListListenerDelete(val clickListener: (recipe: RecipeItemShoppingList) -> Unit) {
    fun onClick(recipeitemshoppinglist: RecipeItemShoppingList) = clickListener(recipeitemshoppinglist)
}

//class UsersListenerDelete(val clickListener: (user: wiktor.app.other.User) -> Unit) {
//    fun onClick(user: wiktor.app.other.User) = clickListener(user)
//}
//class UsersListenerEdit(val clickListener: (user: wiktor.app.other.User) -> Unit) {
//    fun onClick(user: wiktor.app.other.User) = clickListener(user)
//}