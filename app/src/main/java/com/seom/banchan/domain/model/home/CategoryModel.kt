package com.seom.banchan.domain.model.home

import com.seom.banchan.ui.model.home.CategoryMenuModel

data class CategoryModel(
    val id: String,
    val name: String,
    val menus: List<MenuModel>
)

fun CategoryModel.toUiModel(isBest: Boolean = false, cartMenuIds: List<String>) = CategoryMenuModel(
    id = id,
    categoryName = name,
    menus = menus.map { it.toHomeMenuModel(isBest, cartMenuIds) }
)
