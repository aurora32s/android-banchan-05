package com.seom.banchan.ui.model.home

import com.seom.banchan.ui.model.CellType
import com.seom.banchan.ui.model.Model

data class FilterMenuModel(
    override val id: String,
    override val type: CellType = CellType.FILTER_CELL,
) : Model(id, type)
