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
        LinearLayout.LayoutParams.WRAP_CONTENT,
        LinearLayout.LayoutParams.WRAP_CONTENT
    )
    params.setMargins(
        marginHorizontal,
        marginVertical,
        marginHorizontal,
        marginVertical
    )

    val image = ImageView(context).apply { setDrawableRes(imageUrl) }
    image.layoutParams = params

    addView(image)
}