package com.seom.banchan.util.ext

import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.annotation.DrawableRes

fun LinearLayout.addIconImageView(
    @DrawableRes
    imageUrl: Int,
    marginHorizontal: Int = 0,
    marginVertical: Int = 0
) {
    val params = LinearLayout.LayoutParams(
        ViewGroup.LayoutParams.WRAP_CONTENT,
        ViewGroup.LayoutParams.WRAP_CONTENT
    )
    params.setMargins(
        marginHorizontal,
        marginVertical,
        marginHorizontal,
        marginVertical
    )

    val image = ImageView(context).apply { setIconDrawable(imageUrl) }
    image.layoutParams = params

    addView(image)
}