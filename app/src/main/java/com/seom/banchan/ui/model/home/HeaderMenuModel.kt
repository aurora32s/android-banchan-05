package com.seom.banchan.ui.model.home

import com.seom.banchan.ui.model.CellType
import com.seom.banchan.ui.model.Model

data class HeaderMenuModel(
    override val id: Long,
    override val type: CellType = CellType.HEADER_CELL,
    val title: String
) : Model(id, type)
