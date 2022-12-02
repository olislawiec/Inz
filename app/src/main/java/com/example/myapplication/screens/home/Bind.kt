package com.example.myapplication.screens.home

import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide


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


