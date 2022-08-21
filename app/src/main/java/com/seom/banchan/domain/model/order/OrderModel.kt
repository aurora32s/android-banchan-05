package com.seom.banchan.domain.model.order

import com.seom.banchan.ui.model.order.OrderDeliveryState

data class OrderModel(
    val orderId: Long?,
    val createdAt: Long,
    val deliveryState: OrderDeliveryState
)
