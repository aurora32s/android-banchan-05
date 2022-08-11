package com.seom.banchan.data.api.response

import com.seom.banchan.domain.model.CategoryModel

data class Category(
    val id: String,
    val name: String,
    val items: List<Menu>
)

fun Category.toModel() = CategoryModel(
    id = id,
    name = name,
    menus = items.map { it.toModel() }
)