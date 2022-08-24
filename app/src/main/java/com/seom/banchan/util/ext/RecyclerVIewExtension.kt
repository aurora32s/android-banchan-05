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
            var isEndHorizontalScroll = false
            override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
                when(e.action){
                    MotionEvent.ACTION_DOWN -> { // 처음 눌렀을 때
                        lastX = e.x.toInt()
                        lastY = e.y.toInt()
                        isEndHorizontalScroll = // 가로 list에서 마지막 아이템까지 도달했는지
                            (layoutManager as LinearLayoutManager?)?.findLastCompletelyVisibleItemPosition() == adapter!!.itemCount - 1
                    }
                    MotionEvent.ACTION_MOVE -> { // 누르고 움직일 때
                        val distanceX = abs(lastX - e.x)
                        val distanceY = abs(lastY - e.y)
                        if(distanceY > distanceX) {
                            parent.requestDisallowInterceptTouchEvent(false)
                            return false
                        }
                        if(lastX < e.x){ // 왼쪽에서 오른쪽으로 스크롤 할 때는 마지막에 도달한 아이템이라도 스크롤하게 한다.
                            parent.requestDisallowInterceptTouchEvent(true)
                        }else { // 오른쪽에서 왼쪽으로 스크롤할 때, 처음 눌렀을 때의 아이템이 마지막이면 parent가 인터셉트한다,
                            parent.requestDisallowInterceptTouchEvent(!isEndHorizontalScroll)
                        }
                    }
                }
                return false
            }

            override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {}

            override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {}
        }
    )
}
