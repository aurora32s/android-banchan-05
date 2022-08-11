package com.seom.banchan.domain.model

import com.seom.banchan.ui.model.home.CategoryMenuModel

data class CategoryModel(
    val id: String,
    val name: String,
    val menus: List<MenuModel>
)

fun CategoryModel.toUiModel() = CategoryMenuModel(
    id = id,
    categoryName = name,
    menus = menus.map { it.toHomeMenuModel() }
)
