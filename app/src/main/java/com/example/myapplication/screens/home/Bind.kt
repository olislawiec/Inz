package com.example.myapplication.screens.home

import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.myapplication.R

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String) {
    imgView.visibility = View.GONE

        val imgUri = imgUrl?.toUri()?.buildUpon()?.scheme("https")?.build()
        Glide.with(imgView.context)
            .load(imgUri)

            .into(imgView)
        imgView.visibility = View.VISIBLE
    Log.d("Link",imgUrl)

}