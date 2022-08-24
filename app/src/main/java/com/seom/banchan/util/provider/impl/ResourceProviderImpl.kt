package com.seom.banchan.util.provider.impl

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import com.seom.banchan.util.provider.ResourceProvider

class ResourceProviderImpl(
    private val context: Context
) : ResourceProvider {

    override fun getString(resId: Int): String = context.getString(resId)
    override fun getString(resId: Int, vararg formArgs: Any): String =
        context.getString(resId, formArgs)

    override fun getColor(resId: Int): Int = context.getColor(resId)

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun getDrawable(resId: Int): Drawable = context.resources.getDrawable(resId, null)
}