package com.seom.banchan.data.api.response.best

import com.google.gson.annotations.SerializedName
import com.seom.banchan.domain.model.CategoryModel

data class Category(
    @SerializedName("category_id")
    val categoryId: String,
    val name: String,
    val items: List<Menu>
)

fun Category.toModel() = CategoryModel(
    id = categoryId,
    name = name,
    menus = items.map { it.toModel() }
)