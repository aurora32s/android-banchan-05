package com.seom.banchan.ui.model.detail

import com.seom.banchan.ui.model.CellType
import com.seom.banchan.ui.model.Model

data class MenuDetailModel(
    override val id: String,
    override val type: CellType = CellType.MENU_DETAIL_EXTRA_CELL,
    val images: List<String>,
    val name: String,
    val salePrice: Int,
    val normalPrice: Int,
    val point: Int,
    val deliveryInfo: String,
    val deliveryFee: String,
    val detailImages: List<String>
) : Model(id, type)
