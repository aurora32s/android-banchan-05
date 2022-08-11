package com.seom.banchan.domain.model

import com.seom.banchan.ui.model.home.HomeMenuModel

data class MenuModel(
    val id: String,
    val name: String,
    val deliveryType: List<String>,
    val image: String,
    val description: String,
    val salePrice: Int,
    val normalPrice: Int
)

fun MenuModel.toHomeMenuModel() = HomeMenuModel(
    id = id,
    menu = this,
    discountRate = (normalPrice / salePrice * 100)
)
