package com.seom.banchan.ui.model.home

import com.seom.banchan.ui.model.CellType
import com.seom.banchan.ui.model.Model
import com.seom.banchan.ui.model.SortItem

data class SortMenuModel(
    override val id: String,
    override val type: CellType = CellType.SORT_CELL,
    val onSort : (SortItem) -> Unit,
    val sortState : SortItem
) : Model(id, type)
