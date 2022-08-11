package com.seom.banchan.data.api.response

import com.seom.banchan.domain.model.MenuModel
import com.seom.banchan.util.ext.toIntPrice

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

fun Menu.toModel() = MenuModel(
    id = id,
    name = name,
    deliveryType = deliveryType,
    image = image,
    description = description,
    salePrice = salePrice.toIntPrice(),
    normalPrice = normalPrice.toIntPrice()
)