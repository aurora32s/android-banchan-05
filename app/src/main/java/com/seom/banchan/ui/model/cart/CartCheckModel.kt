package com.seom.banchan.ui.model.cart

import com.seom.banchan.ui.model.CellType
import com.seom.banchan.ui.model.Model
import com.seom.banchan.ui.model.home.HomeMenuModel

class CartCheckModel (
    override val id: String,
    override val type: CellType = CellType.CART_CHECK_CELL,
    val atLeastChecked : Boolean
) : Model(id, type)