package com.example.myapplication.screens.calendar

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.RecipeitemcalendarBinding
import com.example.myapplication.localdb.RecipeItem
import com.example.myapplication.localdb.RecipeItemCalendar


class RecipeBookAdapter(private val clicklfordetailslistener: RecipeCalendarListener, private val clickforeditlistener: RecipeCalendarListenerEdit, private val clickforremovelistener: RecipeCalendarListenerDelete) : ListAdapter<RecipeItemCalendar, RecipeCalendarViewHolder>(
    RecipeItemCalDiffCallback()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeCalendarViewHolder {
        return RecipeCalendarViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: RecipeCalendarViewHolder, position: Int) {
        holder.bind(getItem(position), clicklfordetailslistener, clickforeditlistener, clickforremovelistener)
    }


}
class RecipeCalendarViewHolder private constructor(val binding: RecipeitemcalendarBinding): RecyclerView.ViewHolder(binding.root) {
    fun bind(
        recipe: RecipeItemCalendar,
        clicklfordetailslistener: RecipeCalendarListener,
        clickforeditlistener: RecipeCalendarListenerEdit,
        clickforremovelistener: RecipeCalendarListenerDelete


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
        fun from(parent: ViewGroup): RecipeCalendarViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = RecipeitemcalendarBinding.inflate(layoutInflater, parent, false)
            return RecipeCalendarViewHolder(binding)
        }
    }
}
class RecipeItemCalDiffCallback: DiffUtil.ItemCallback<RecipeItemCalendar>() {
    override fun areItemsTheSame(oldItem: RecipeItemCalendar, newItem: RecipeItemCalendar): Boolean {
        return false
    }

    override fun areContentsTheSame(oldItem: RecipeItemCalendar, newItem: RecipeItemCalendar): Boolean {
        return false
    }


}
class RecipeCalendarListener(val clickListener: (recipecal: RecipeItemCalendar) -> Unit) {
    fun onClick(recipeitemcal: RecipeItemCalendar) = clickListener(recipeitemcal)
}
class RecipeCalendarListenerDelete(val clickListener: (recipecal: RecipeItemCalendar) -> Unit) {
    fun onClick(recipeitemcal: RecipeItemCalendar) = clickListener(recipeitemcal)
}
class RecipeCalendarListenerEdit(val clickListener: (recipecal: RecipeItemCalendar) -> Unit) {
    fun onClick(recipeitemcal: RecipeItemCalendar) = clickListener(recipeitemcal)
}
//class UsersListenerDelete(val clickListener: (user: wiktor.app.other.User) -> Unit) {
//    fun onClick(user: wiktor.app.other.User) = clickListener(user)
//}
//class UsersListenerEdit(val clickListener: (user: wiktor.app.other.User) -> Unit) {
//    fun onClick(user: wiktor.app.other.User) = clickListener(user)
//}