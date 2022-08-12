package com.seom.banchan.util.ext

import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy


@BindingAdapter("imageSrc")
fun ImageView.load(
    imageUrl: String
) {
    Glide.with(this.context)
        .load(imageUrl)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .centerCrop()
        .into(this)
}

fun ImageView.setIconDrawable(
    @DrawableRes
    imageId: Int
) {
    setImageDrawable(ContextCompat.getDrawable(context, imageId))
}