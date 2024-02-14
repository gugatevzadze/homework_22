package com.example.homework_22.presentation.extension

import com.bumptech.glide.Glide
import com.example.homework_22.R
import de.hdodenhof.circleimageview.CircleImageView

fun CircleImageView.loadImage(url: String?) {
    if (url != null) {
        Glide.with(context)
            .load(url)
            .placeholder(R.drawable.ic_launcher_background)
            .into(this)
    } else {
        this.setImageResource(R.drawable.pfp_default_2)
    }
}