package com.seom.banchan.domain.model.order

import com.seom.banchan.ui.model.order.OrderMenuUiModel

data class OrderItemModel(
    val menuId: String,
    val orderId: Long,
    val name: String,
    val image: String?,
    val salePrice: Int,
    val count: Int
)

fun OrderItemModel.toUiModel() = OrderMenuUiModel(
    menuName = name,
    image = image,
    count = count,
    salePrice = salePrice
)