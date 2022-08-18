package com.seom.banchan.domain.model.home

import com.seom.banchan.ui.model.CellType
import com.seom.banchan.ui.model.home.HomeMenuLargeModel
import com.seom.banchan.ui.model.home.HomeMenuModel
import java.io.Serializable
import java.lang.Math.ceil

data class MenuModel(
    val id: String,
    val name: String,
    val deliveryType: List<String>,
    val image: String,
    val description: String,
    val salePrice: Int,
    val normalPrice: Int,
    val recentTime : String = ""
): Serializable

fun MenuModel.toHomeMenuModel(isBest : Boolean = false,cellType: CellType = CellType.MENU_CELL) = HomeMenuModel(
    id = id,
    isBest = isBest,
    menu = this,
    type = cellType,
    discountRate = if (normalPrice == 0) 0 else ceil((1 - (salePrice / normalPrice.toDouble())) * 100).toInt()
)

fun MenuModel.toHomeMenuLinearModel() = HomeMenuModel(
    id = id,
    type = CellType.MENU_LARGE_CELL,
    menu = this,
    discountRate = if (normalPrice == 0) 0 else ceil((1 - (salePrice / normalPrice.toDouble())) * 100).toInt()
)
