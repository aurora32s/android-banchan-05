package com.seom.banchan.data.db.entity

import kotlinx.coroutines.flow.Flow

data class OrderInfoEntity(
    val order: Flow<OrderEntity>, // 주문 정보
    val menus: List<OrderItemEntity>
)