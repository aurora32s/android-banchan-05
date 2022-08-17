package com.seom.banchan.ui.model.cart

import com.seom.banchan.ui.model.CellType
import com.seom.banchan.ui.model.Model

class CartMenuModel (
    override val id: String,
    override val type: CellType = CellType.CART_MENU_CELL,
) : Model(id, type)