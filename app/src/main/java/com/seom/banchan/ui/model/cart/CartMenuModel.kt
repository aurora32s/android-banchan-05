package com.seom.banchan.ui.model.cart

import com.seom.banchan.domain.model.home.MenuModel
import com.seom.banchan.ui.model.CellType
import com.seom.banchan.ui.model.Model
import com.seom.banchan.ui.model.home.HomeMenuModel

data class CartMenuModel (
    override val id: String,
    override val type: CellType = CellType.CART_MENU_CELL,
    val menu : MenuModel,
    var count : Int = 1,
    var checked : Boolean = true,
) : Model(id, type)