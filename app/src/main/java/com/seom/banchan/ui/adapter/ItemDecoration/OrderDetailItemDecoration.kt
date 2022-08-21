package com.seom.banchan.ui.adapter.ItemDecoration

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.core.view.setPadding
import androidx.recyclerview.widget.RecyclerView
import com.seom.banchan.ui.model.CellType
import com.seom.banchan.util.ext.fromDpToPx

class OrderDetailItemDecoration(
    val context: Context,
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        val viewHolder = parent.getChildViewHolder(view)

        val cellType = CellType.getCellTypeByViewType(viewHolder.itemViewType) ?: return

        when (cellType) {
            CellType.ORDER_MENU_CELL -> {
                view.setPadding(
                    8f.fromDpToPx(),
                    8f.fromDpToPx(),
                    8f.fromDpToPx(),
                    8f.fromDpToPx()
                )
            }
            CellType.ORDER_STATE_CELL -> {
                outRect.bottom = 16f.fromDpToPx()
                view.setPadding(16f.fromDpToPx())
            }
            else -> {
                view.setPadding(16f.fromDpToPx())
            }
        }
    }
}