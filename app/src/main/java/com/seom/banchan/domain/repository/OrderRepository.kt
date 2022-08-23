package com.seom.banchan.domain.repository

import com.seom.banchan.domain.model.order.OrderDetailModel
import com.seom.banchan.domain.model.order.OrderListModel
import kotlinx.coroutines.flow.Flow

interface OrderRepository {
    /**
     * 주문 내역 리스트 요청
     */
    fun getOrderList(): Flow<List<OrderListModel>>

    /**
     * 주문 상세 내역 요청
     */
    suspend fun getDetailOrderInfo(orderId: Long): Result<OrderDetailModel>

    /**
     * 특정 주문 배달 완료 처리
     */
    suspend fun setDeliveryCompletedById(orderId: Long): Result<Int>
}