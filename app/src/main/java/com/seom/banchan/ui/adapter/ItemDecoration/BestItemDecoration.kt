package com.seom.banchan.ui.adapter.ItemDecoration

import android.content.Context
import android.graphics.Rect
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.seom.banchan.util.ext.dpToPx
import kotlin.math.roundToInt

class BestItemDecoration(
    val context: Context
) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        val spacing = 4.dpToPx(context)

        outRect.left = spacing
        outRect.right = spacing
    }
}


