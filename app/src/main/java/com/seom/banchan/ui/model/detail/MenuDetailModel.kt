package com.seom.banchan.ui.model.detail

data class MenuDetailModel(
    val id: String,
    val images: List<String>,
    val name: String,
    val salePrice: Int,
    val normalPrice: Int,
    val point: Int,
    val deliveryInfo: String,
    val deliveryFee: String,
    val detailImages: List<String>
)
