package com.example.homework_22.presentation.extension

import android.widget.ImageView
import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.Glide
import com.example.homework_22.R

fun AppCompatImageView.loadImage(url: String?) {
    Glide.with(context)
        .load(url)
        .placeholder(R.drawable.ic_launcher_background)
        .into(this)
}