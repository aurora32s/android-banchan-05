package com.seom.banchan.domain.model

data class CategoryModel(
    val id: String,
    val name: String,
    val menus: List<MenuModel>
)
