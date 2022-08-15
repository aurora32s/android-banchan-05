package com.seom.banchan.ui.adapter

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.roundToInt

class ItemDecoration(
    context: Context,
    isGrid: Boolean
) {
    val decoration = object : RecyclerView.ItemDecoration() {
        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            super.getItemOffsets(outRect, view, parent, state)

            val idx = parent.getChildAdapterPosition(view) - 2
            if (idx < 0) return

            val spanCount = if(isGrid) 2 else 1
            val column = idx % spanCount
            val margin = 16.dpToPx(context)
            val spacing = 8.dpToPx(context)

            outRect.left =
                spacing - column * spacing / spanCount
            outRect.right =
                (column + 1) * spacing / spanCount

            when (column) {
                0 -> {
                    outRect.left = margin
                }
                spanCount - 1 -> {
                    outRect.right = margin
                }
            }

            outRect.bottom = 32.dpToPx(context)
        }
    }
}

fun Int.dpToPx(context : Context): Int {
    val density = context.resources.displayMetrics.density
    return (this * density).roundToInt()
}

