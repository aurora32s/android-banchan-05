package com.seom.banchan.data.api.response

data class Menu(
    val id: String,
    val alt: String,
    val name: String,
    val deliveryType: List<String>,
    val badge: List<String>,
    val image: String,
    val description: String,
    val salePrice: String,
    val normalPrice: String
)