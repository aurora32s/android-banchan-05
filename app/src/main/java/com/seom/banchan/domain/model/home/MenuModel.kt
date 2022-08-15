package com.seom.banchan.domain.model.home

import com.seom.banchan.ui.model.home.HomeMenuGridModel
import com.seom.banchan.ui.model.home.HomeMenuLinearModel
import com.seom.banchan.ui.model.home.HomeMenuModel
import java.lang.Math.ceil

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
    discountRate = if (normalPrice == 0) 0 else ceil((1 - (salePrice / normalPrice.toDouble())) * 100).toInt()
)

fun MenuModel.toHomeMenuLinearModel() = HomeMenuLinearModel(
    id = id,
    menu = this,
    discountRate = if (normalPrice == 0) 0 else ceil((1 - (salePrice / normalPrice.toDouble())) * 100).toInt()
)

fun MenuModel.toHomeMenuGridModel() = HomeMenuGridModel(
    id = id,
    menu = this,
    discountRate = if (normalPrice == 0) 0 else ceil((1 - (salePrice / normalPrice.toDouble())) * 100).toInt()
)
