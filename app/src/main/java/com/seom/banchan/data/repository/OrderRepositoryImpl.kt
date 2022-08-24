package com.seom.banchan.data.repository

import com.seom.banchan.data.source.OrderDataSource
import com.seom.banchan.domain.model.order.OrderDetailModel
import com.seom.banchan.domain.model.order.OrderItemModel
import com.seom.banchan.domain.model.order.OrderListModel
import com.seom.banchan.domain.repository.OrderRepository
import com.seom.banchan.worker.model.DeliveryAlarmModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class OrderRepositoryImpl @Inject constructor(
    private val orderDataSource: OrderDataSource
) : OrderRepository {

    // 주문 내역 리스트 flow 요청
    override fun getOrderList(): Flow<List<OrderListModel>> {
        return orderDataSource.getOrderList()
    }

    // 주문 내역 상세 정보 요청
    override suspend fun getDetailOrderInfo(orderId: Long): Result<OrderDetailModel> {
        return orderDataSource.getDetailOrderById(orderId)
    }

    // 특정 주문 배달 완료 처리
    override suspend fun setDeliveryCompletedById(orderId: Long): Result<Int> {
        return orderDataSource.setDeliveryCompletedById(orderId)
    }

    // 주문 아이템 추가하기
    override suspend fun addOrder(orderItems: List<OrderItemModel>): Result<Long> {
        return orderDataSource.insertOrder(orderItems)
    }

    // 특정 주문의 배달 정보 받아오기
    override suspend fun getDeliveryInfoById(orderId: Long): Result<DeliveryAlarmModel> {
        return orderDataSource.getDeliveryInfoById(orderId)
    }
}