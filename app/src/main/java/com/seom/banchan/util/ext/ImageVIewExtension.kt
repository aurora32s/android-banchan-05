package com.seom.banchan.util.ext

import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CenterInside
import com.bumptech.glide.load.resource.bitmap.RoundedCorners


@BindingAdapter(value = ["imageUrl", "corner"], requireAll = false)
fun ImageView.load(
    imageUrl: String,
    corner: Float = 0f
) {
    Glide.with(this.context)
        .load(imageUrl)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .apply {
            if (corner > 0) transform(CenterInside(), RoundedCorners(corner.fromDpToPx()))
        }
        .into(this)
}

fun ImageView.setIconDrawable(
    @DrawableRes
    imageId: Int
) {
    setImageDrawable(ContextCompat.getDrawable(context, imageId))
}