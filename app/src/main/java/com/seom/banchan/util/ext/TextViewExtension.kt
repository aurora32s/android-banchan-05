package com.seom.banchan.util.ext

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