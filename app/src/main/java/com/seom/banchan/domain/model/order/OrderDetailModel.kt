package com.seom.banchan.domain.model.order

import kotlinx.coroutines.flow.Flow

/**
 * 주문 상세 화면에서 사용할 data들
 */
data class OrderDetailModel(
    val order: Flow<OrderModel>,
    val menus: List<OrderItemModel>
)
