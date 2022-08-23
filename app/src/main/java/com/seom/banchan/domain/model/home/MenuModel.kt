package com.seom.banchan.domain.model.home

import com.seom.banchan.domain.model.cart.CartMenuModel
import com.seom.banchan.ui.model.CellType
import com.seom.banchan.ui.model.home.HomeMenuModel
import java.io.Serializable
import java.lang.Math.ceil

data class MenuModel(
    val id: String,
    val name: String,
    val deliveryType: List<String>,
    val image: String,
    val description: String,
    val salePrice: Int,
    val normalPrice: Int,
    val recentTime : String = ""
): Serializable


fun MenuModel.toHomeMenuModel(
    isBest: Boolean = false,
    cartMenus: List<CartMenuModel> = emptyList(),
    cellType: CellType = CellType.MENU_CELL,
): HomeMenuModel {
    val cartMenu = cartMenus.find { it.menuId == id }
    return HomeMenuModel(
        id = id,
        type = cellType,
        isBest = isBest,
        menu = this,
        count = cartMenu?.count ?: 1,
        discountRate = if (normalPrice == 0) 0 else ceil((1 - (salePrice / normalPrice.toDouble())) * 100).toInt(),
        isLoadedCart = cartMenu != null
    )
}