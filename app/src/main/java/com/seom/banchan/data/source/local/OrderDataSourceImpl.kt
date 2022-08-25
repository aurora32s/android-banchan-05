package com.seom.banchan.data.source.local

import com.seom.banchan.data.db.dao.OrderDao
import com.seom.banchan.data.db.entity.*
import com.seom.banchan.data.source.OrderDataSource
import com.seom.banchan.domain.model.order.OrderDetailModel
import com.seom.banchan.domain.model.order.OrderItemModel
import com.seom.banchan.domain.model.order.OrderListModel
import com.seom.banchan.ui.model.order.OrderInfoModel
import com.seom.banchan.worker.model.DeliveryAlarmModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class OrderDataSourceImpl @Inject constructor(
    private val orderDao: OrderDao
) : OrderDataSource {

    /**
     * 주문내역 추가
     * 1. 주문 추가
     * 2. 추가된 주문 id로 메뉴들 추가
     */
    override suspend fun insertOrder(orderItems: List<OrderItemModel>): Result<Long> = try {
        val orderId = orderDao.insertOrder(
            OrderEntity(
                createdAt = System.currentTimeMillis(),
                expectedTime = 20 * 1000
            )
        )
        val menus = orderItems.map { it.toEntity(orderId) }
        orderDao.insertOrderItem(menus)

        throw java.lang.Exception()
        Result.success(orderId)
    } catch (exception: Exception) {
        Result.failure(exception)
    }

    // 주문 내역 리스트 요청
    override fun getOrderList(): Flow<List<OrderListModel>> {
        return orderDao.getOrderList().map {
            it.map { it.toModel() }
        }
    }

    override suspend fun getDetailOrderById(orderId: Long): Result<OrderDetailModel> = try {
        val result = orderDao.getOrderInfoById(orderId)
        Result.success(
            OrderDetailModel(
                order = result.order.map { it.toModel() },
                menus = result.menus.map { it.toModel() },
            )
        )
    } catch (exception: Exception) {
        Result.failure(exception)
    }

    override suspend fun setDeliveryCompletedById(orderId: Long): Result<Int> = try {
        val result = orderDao.setDeliveryCompletedById(orderId)
        Result.success(result)
    } catch (exception: Exception) {
        Result.failure(exception)
    }

    override suspend fun getDeliveryInfoById(orderId: Long): Result<DeliveryAlarmModel> = try {
        val result = orderDao.getDeliveryInfoById(orderId)
        Result.success(result.toModel())
    } catch (exception: Exception) {
        Result.failure(exception)
    }
}