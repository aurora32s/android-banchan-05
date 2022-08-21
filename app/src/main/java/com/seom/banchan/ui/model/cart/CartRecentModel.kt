package com.seom.banchan.ui.model.cart

import com.seom.banchan.ui.model.CellType
import com.seom.banchan.ui.model.Model

data class CartRecentModel (
    override val id: String = CellType.CART_RECENT_CELL.name,
    override val type: CellType = CellType.CART_RECENT_CELL,
    val recentMenus : List<Model> = listOf()
) : Model(id, type)


