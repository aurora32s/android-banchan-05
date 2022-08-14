package com.seom.banchan.ui.model.home

import com.seom.banchan.ui.model.CellType
import com.seom.banchan.ui.model.Model
import com.seom.banchan.ui.model.Sort

data class TotalMenuModel(
    override val id: String,
    override val type: CellType = CellType.TOTAL_CELL,
    val count : Int,
    var position : Int,
    val sortByItems : List<Sort>,
    val onSort : (Int) -> Unit
) : Model(id, type)
