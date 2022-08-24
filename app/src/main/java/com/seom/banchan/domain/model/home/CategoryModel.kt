package com.seom.banchan.domain.model.home

import com.seom.banchan.domain.model.cart.CartMenuModel
import com.seom.banchan.ui.model.CellType
import com.seom.banchan.ui.model.home.CategoryMenuModel

data class CategoryModel(
    val id: String,
    val name: String,
    val menus: List<MenuModel>
)

fun CategoryModel.toUiModel(cartMenus: List<CartMenuModel>) = CategoryMenuModel(
    id = id,
    categoryName = name,
    menus = menus.map { it.toHomeMenuModel(cellType = CellType.MENU_BEST_CELL,cartMenus = cartMenus) }
)
