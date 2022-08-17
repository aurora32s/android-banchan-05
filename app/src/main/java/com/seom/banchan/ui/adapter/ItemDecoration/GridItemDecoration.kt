package com.seom.banchan.ui.adapter.ItemDecoration

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.seom.banchan.util.ext.dpToPx
import kotlin.math.roundToInt

class GridItemDecoration(
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

            val idx = parent.getChildAdapterPosition(view) - 3
            if (idx < 0) return

            val spanCount = if(isGrid) 2 else 1
            val column = idx % spanCount
            val marginToParent = 16.dpToPx(context)
            val marginToItem = 4.dpToPx(context)


            when (column) {
                0 -> {
                    outRect.left = marginToParent
                    outRect.right = marginToItem
                }
                spanCount - 1 -> {
                    outRect.right = marginToParent
                    outRect.left = marginToItem
                }
            }

            outRect.bottom = 32.dpToPx(context)
        }
    }
}
