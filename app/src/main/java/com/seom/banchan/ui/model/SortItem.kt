package com.seom.banchan.ui.model

import androidx.annotation.StringRes
import com.seom.banchan.R
import com.seom.banchan.data.api.SortCriteria

enum class SortItem(
    @StringRes val title : Int,
    val sortCriteria: SortCriteria,
){
    BASE(
        title = R.string.sort_base,
        sortCriteria = SortCriteria.BASE
    ),
    DESCENDING(
        title = R.string.sort_descending,
        sortCriteria = SortCriteria.DESCENDING
    ),
    ASCENDING(
        title = R.string.sort_ascending,
        sortCriteria = SortCriteria.ASCENDING
    ),
    DISCOUNT_RATE(
        title = R.string.sort_discount,
        sortCriteria = SortCriteria.DISCOUNT_RATE
    )
}

fun defaultSortItems() = listOf(
    SortItem.BASE,
    SortItem.DESCENDING,
    SortItem.ASCENDING,
    SortItem.DISCOUNT_RATE
)