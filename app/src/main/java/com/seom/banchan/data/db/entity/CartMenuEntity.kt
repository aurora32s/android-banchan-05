package com.seom.banchan.data.db.entity

data class CartMenuEntity(
    val id: Int,
    val menuId: String,
    val name: String,
    val image: String,
    val salePrice: Int,
    val count: Int
)
