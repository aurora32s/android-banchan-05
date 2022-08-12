package com.seom.banchan.ui.model.home

import com.seom.banchan.ui.model.CellType
import com.seom.banchan.ui.model.Model

data class TotalMenuModel(
    override val id: String,
    override val type: CellType = CellType.TOTAL_CELL,
    val count : Int
) : Model(id, type)
