package com.seom.banchan.util.ext

import android.graphics.Paint
import android.widget.TextView
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter

@BindingAdapter("text")
fun TextView.setText(data: String?) {
    if (data.isNullOrBlank())
        isGone = true
    else {
        isVisible = true
        text = data
    }
}

@BindingAdapter("visibilityByData")
fun TextView.setVisibilityByData(data: Int?) {
    if (data == null || data == 0) {
        isGone = true
    } else {
        isVisible = true
    }
}

@BindingAdapter("strikeThrough")
fun TextView.strikeThrough(use: Boolean) {
    paintFlags = if (use) paintFlags or Paint.STRIKE_THRU_TEXT_FLAG else 0
}