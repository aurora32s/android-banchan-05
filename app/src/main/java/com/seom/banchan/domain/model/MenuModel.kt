package com.seom.banchan.domain.model

data class MenuModel(
    val id: String,
    val name: String,
    val deliveryType: List<String>,
    val image: String,
    val description: String,
    val salePrice: Int,
    val normalPrice: Int
)
