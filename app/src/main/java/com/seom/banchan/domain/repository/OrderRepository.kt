package com.seom.banchan.domain.repository

import com.seom.banchan.domain.model.order.OrderListModel
import kotlinx.coroutines.flow.Flow

interface OrderRepository {
    /**
     * 주문 내역 리스트 요청
     */
    fun getOrderList(): Flow<List<OrderListModel>>
}