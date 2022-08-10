package com.seom.banchan.ui.model.home

import com.seom.banchan.ui.model.CellType
import com.seom.banchan.ui.model.Model

data class CategoryMenuModel(
    override val id: Long,
    override val type: CellType = CellType.MENU_LIST_CELL,
    val categoryName: String,
    val menus: List<HomeMenuModel>
) : Model(id, type)
