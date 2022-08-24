package com.seom.banchan.util.ext

import android.content.Context
import android.view.MotionEvent
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.seom.banchan.ui.model.CellType
import kotlin.math.abs

fun RecyclerView.setGridLayoutManager(context : Context){
    val gridLayoutManager = GridLayoutManager(context, 2)
    gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
        override fun getSpanSize(position: Int): Int {
            return when (adapter?.getItemViewType(position)) {
                CellType.MENU_CELL.ordinal,
                CellType.SORT_CELL.ordinal,
                CellType.FILTER_CELL.ordinal,
                CellType.TOTAL_CELL.ordinal,
                CellType.MENU_RECENT_CELL.ordinal-> 1
                else -> 2
            }
        }
    }
    layoutManager = gridLayoutManager
}

fun RecyclerView.addHorizontalAndVerticalScrollListener() {
    addOnItemTouchListener(
        object : RecyclerView.OnItemTouchListener{

            var lastX = 0
            var lastY = 0

            override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
                when(e.action){
                    MotionEvent.ACTION_DOWN -> { // 처음 눌렀을 때
                        lastX = e.x.toInt()
                        lastY = e.y.toInt()
                    }
                    MotionEvent.ACTION_MOVE -> { // 누르고 움직일 때
                        val distanceX = abs(lastX - e.x)
                        val distanceY = abs(lastY - e.y)
                        if(distanceY > distanceX) {
                            parent.requestDisallowInterceptTouchEvent(false)
                            return false
                        }
                        parent.requestDisallowInterceptTouchEvent(true)
                    }
                }
                return false
            }

            override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {}

            override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {}
        }
    )
}
