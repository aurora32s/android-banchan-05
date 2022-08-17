package com.seom.banchan.ui.model.home

import com.seom.banchan.ui.model.CellType
import com.seom.banchan.ui.model.Model
import com.seom.banchan.ui.model.SortItem

data class FilterMenuModel(
    override val id: String,
    override val type: CellType = CellType.FILTER_CELL,
    val onToggle : (Boolean) -> Unit,
    val togglePosition : Int,
    var position : Int,
    val sortByItems : List<SortItem>,
    val onSort : (Int) -> Unit
) : Model(id, type)
