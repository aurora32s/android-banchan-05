package com.seom.banchan.ui.model.home

import com.seom.banchan.ui.model.CellType
import com.seom.banchan.ui.model.Model

data class CategoryMenuModel(
    override val id: String,
    override val type: CellType = CellType.MENU_LIST_CELL,
    val categoryName: String,
    val menus: List<Model>
) : Model(id, type)
