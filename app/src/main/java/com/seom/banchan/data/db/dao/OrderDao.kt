package com.seom.banchan.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.seom.banchan.data.db.entity.OrderEntity
import com.seom.banchan.data.db.entity.OrderItemEntity

/**
 * 주문 내역 관련 DB
 */
@Dao
interface OrderDao {

    /**
     * 주문 내역 추가
     */
    @Insert
    suspend fun insertOrder(order: OrderEntity): Long

    /**
     * 주문 아이템들 추가
     */
    @Insert
    suspend fun insertOrderItem(orderItems: List<OrderItemEntity>)

    /**
     * 주문 내역 리스트 요청
     */
}