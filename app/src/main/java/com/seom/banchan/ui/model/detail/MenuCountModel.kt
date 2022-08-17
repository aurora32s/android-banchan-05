package com.seom.banchan.ui.model.detail

import com.seom.banchan.ui.model.CellType
import com.seom.banchan.ui.model.Model

data class MenuCountModel(
    override val id: String,
    override val type: CellType = CellType.DETAIL_COUNT_CELL,
    val count: Int
) : Model(id, type)
