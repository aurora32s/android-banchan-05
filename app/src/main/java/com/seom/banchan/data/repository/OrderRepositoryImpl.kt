package com.seom.banchan.data.repository

import com.seom.banchan.data.source.OrderDataSource
import com.seom.banchan.domain.model.order.OrderDetailModel
import com.seom.banchan.domain.model.order.OrderListModel
import com.seom.banchan.domain.repository.OrderRepository
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
}