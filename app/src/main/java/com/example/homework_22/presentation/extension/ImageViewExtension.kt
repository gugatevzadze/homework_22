package com.example.homework_22.presentation.extension

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.homework_22.R

fun ImageView.loadImage(url: String?) {
    if (url != null) {
        Glide.with(context)
            .load(url)
            .placeholder(R.drawable.ic_launcher_background)
            .into(this)
    } else {
        this.setImageDrawable(null)
    }
}