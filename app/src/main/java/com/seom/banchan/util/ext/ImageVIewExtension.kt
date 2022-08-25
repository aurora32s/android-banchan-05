package com.seom.banchan.util.ext

import android.annotation.SuppressLint
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CenterInside
import com.bumptech.glide.load.resource.bitmap.RoundedCorners

@BindingAdapter(value = ["imageUrl", "corner"], requireAll = false)
fun ImageView.load(
    imageUrl: String?,
    corner: Float = 0f
) {
    if (imageUrl == null) return
    Glide.with(this.context)
        .load(imageUrl)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .apply {
            if (corner > 0) transform(CenterInside(), RoundedCorners(corner.fromDpToPx()))
        }
        .into(this)
}

@BindingAdapter(value = ["imageId", "corner"], requireAll = false)
fun ImageView.setIconDrawable(
    @DrawableRes
    imageId: Int,
    circleCrop: Boolean = false
) {
    Glide.with(this.context)
        .load(imageId)
        .apply {
            if (circleCrop) circleCrop()
        }
        .into(this)
}

@SuppressLint("UseCompatLoadingForDrawables")
fun ImageView.setDrawableRes(
    @DrawableRes
    imageId: Int
) {
    this.setImageDrawable(
        this.context.getDrawable(imageId)
    )
}