package com.seom.banchan.ui.model

import androidx.annotation.StringRes
import com.seom.banchan.R
import com.seom.banchan.data.api.SortCriteria

data class Sort(
    @StringRes val name : Int,
    val sortCriteria: SortCriteria,
    var isChecked : Boolean = false
)
fun defaultSortItems() = listOf(
    Sort(
        R.string.sort_base,
        SortCriteria.BASE,
        true
    ),
    Sort(
        R.string.sort_descending,
        SortCriteria.DESCENDING,
        false
    ),
    Sort(
        R.string.sort_ascending,
        SortCriteria.ASCENDING,
        false
    ),
    Sort(
        R.string.sort_discount,
        SortCriteria.DISCOUNT_RATE,
        true
    )
)

fun List<Sort>.selectedSortItem(position: Int) {
    forEachIndexed { index, sort ->
        sort.isChecked = index == position
    }
}
