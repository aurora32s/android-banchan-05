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
        SortCriteria.DESCENDING
    ),
    Sort(
        R.string.sort_ascending,
        SortCriteria.ASCENDING
    ),
    Sort(
        R.string.sort_discount,
        SortCriteria.DISCOUNT_RATE
    )
)
