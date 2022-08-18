package com.seom.banchan.ui.model.cart

import com.seom.banchan.ui.model.CellType
import com.seom.banchan.ui.model.Model

data class CartMenuModel (
    override val id: String,
    override val type: CellType = CellType.CART_MENU_CELL,
    val price : Int = 3000,
    var checked : Boolean = true,
    val onCheck : (CartMenuModel) -> Unit
) : Model(id, type)