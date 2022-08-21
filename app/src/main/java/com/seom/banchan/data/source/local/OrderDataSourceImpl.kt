package com.seom.banchan.data.source.local

import com.seom.banchan.data.db.dao.OrderDao
import com.seom.banchan.data.db.entity.OrderEntity
import com.seom.banchan.data.db.entity.OrderItemEntity
import com.seom.banchan.data.db.entity.OrderListEntity
import com.seom.banchan.data.db.entity.toModel
import com.seom.banchan.data.source.OrderDataSource
import com.seom.banchan.domain.model.order.OrderDetailModel
import com.seom.banchan.domain.model.order.OrderListModel
import com.seom.banchan.ui.model.order.OrderInfoModel
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
    override suspend fun insertOrder(): Result<Long> = try {
        val orderId = orderDao.insertOrder(
            OrderEntity(
                createdAt = System.currentTimeMillis(),
                expectedTime = 10*1000
            )
        )
        val menus = listOf(
            OrderItemEntity(
                menuId = "HBDEF",
                orderId = orderId,
                name = "오리 주물럭_반조리",
                image = "http://public.codesquad.kr/jk/storeapp/data/main/1155_ZIP_P_0081_T.jpg",
                salePrice = 12640,
                count = 1
            ),
            OrderItemEntity(
                menuId = "HF778",
                orderId = orderId,
                name = "소갈비찜",
                image = "http://public.codesquad.kr/jk/storeapp/data/main/349_ZIP_P_0024_T.jpg",
                salePrice = 26010,
                count = 3
            ),
            OrderItemEntity(
                menuId = "HBBCC",
                orderId = orderId,
                name = "새콤달콤 오징어무침",
                image = "http://public.codesquad.kr/jk/storeapp/data/side/48_ZIP_P_5008_T.jpg",
                salePrice = 6000,
                count = 2
            )
        )
        orderDao.insertOrderItem(menus)

        Result.success(1L)
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
}