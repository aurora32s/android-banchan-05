package com.seom.banchan.ui.model.cart

import com.seom.banchan.ui.model.CellType
import com.seom.banchan.ui.model.Model

class CartRecentModel (
    override val id: String,
    override val type: CellType = CellType.CART_RECENT_CELL,
) : Model(id, type)