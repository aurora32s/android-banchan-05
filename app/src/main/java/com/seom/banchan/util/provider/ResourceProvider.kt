package com.seom.banchan.util.provider

import android.graphics.drawable.Drawable
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

interface ResourceProvider {
    fun getString(@StringRes resId: Int): String
    fun getString(@StringRes resId: Int, vararg formArgs: Any): String
    fun getColor(@ColorRes resId: Int): Int
    fun getDrawable(@DrawableRes resId: Int): Drawable
}