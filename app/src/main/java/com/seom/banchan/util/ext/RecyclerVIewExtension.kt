package com.seom.banchan.util.ext

import android.content.Context
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.seom.banchan.ui.model.CellType

fun RecyclerView.setGridLayoutManager(context : Context){
    val gridLayoutManager = GridLayoutManager(context, 2)
    gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
        override fun getSpanSize(position: Int): Int {
            return when (adapter?.getItemViewType(position)) {
                CellType.MENU_CELL.ordinal -> 1
                else -> 2
            }
        }
    }
    layoutManager = gridLayoutManager
}