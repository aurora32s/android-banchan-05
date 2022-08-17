package com.seom.banchan.ui.model.cart

import com.seom.banchan.ui.model.CellType
import com.seom.banchan.ui.model.Model

class CartOrderModel (
    override val id: String,
    override val type: CellType = CellType.CART_ORDER_CELL,
) : Model(id, type)