package com.example.myapplication.screens.home

import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.myapplication.localdb.RecipeItemShoppingList
import com.example.myapplication.screens.recipebook.AddeditrecipeViewModel
import java.text.SimpleDateFormat
import java.util.*


@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    if(!imgUrl.isNullOrEmpty()) {
        imgView.visibility = View.GONE

        val imgUri = imgUrl?.toUri()?.buildUpon()?.scheme("https")?.build()
        Glide.with(imgView.context)
            .load(imgUri)

            .into(imgView)
        imgView.visibility = View.VISIBLE

    }
}


@BindingAdapter("imageUrl2")
fun bindImage2(imgView: ImageView, imgUrl: String?) {
    if(!imgUrl.isNullOrEmpty()){

    imgView.visibility = View.GONE

    val imgUri = imgUrl?.toUri()?.buildUpon()?.scheme("https")?.build()
    Glide.with(imgView.context)
        .load(imgUri)

        .into(imgView)
    imgView.visibility = View.VISIBLE
    }
}
@BindingAdapter("imageUrl3")
fun bindImage3(imgView: ImageView, imgUrl: String ) {

    if(!imgUrl.isNullOrEmpty()) {
        imgView.visibility = View.GONE

        val imgUri = imgUrl?.toUri()?.buildUpon()?.scheme("https")?.build()
        Glide.with(imgView.context)
            .load(imgUri)

            .into(imgView)
        imgView.visibility = View.VISIBLE

    }
}
@BindingAdapter("showdate")
fun TextView.setText(date: String){
    val dateFormatter = SimpleDateFormat("dd/MM/yyyy")
    val dateformated = dateFormatter.format(Date(date.toLong()))
    setText(dateformated)
}

@BindingAdapter("showshoppinglistitem")
fun TextView.setText(recipe: RecipeItemShoppingList){
   setText(recipe.recipeShoppingList.replace("#"," ").replace("|","\n"))
}

@BindingAdapter("shoppingitemname")
fun TextView.setName(recipe: RecipeItemShoppingList){
    if(recipe.recipeName.isNullOrEmpty()){
        visibility= View.GONE
    }
    setText(recipe.recipeName)
}


