package com.seom.banchan.ui.model.home

import com.seom.banchan.data.api.SortCriteria
import com.seom.banchan.ui.model.CellType
import com.seom.banchan.ui.model.Model
import com.seom.banchan.ui.model.Sort
import com.seom.banchan.ui.model.defaultSortItems

data class TotalMenuModel(
    override val id: String,
    override val type: CellType = CellType.TOTAL_CELL,
    val count : Int,
    val sortByItems : List<Sort> = defaultSortItems(),
    val onSort : (SortCriteria) -> Unit
) : Model(id, type)
