package com.seom.banchan.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.seom.banchan.ui.model.order.OrderDeliveryState

/**
 * 주문 내역이 담겨 있는 table
 */
@Entity(tableName = "order_table")
data class OrderEntity(
    @PrimaryKey
    @ColumnInfo(name = "order_id") // order id
    val orderId: Long? = null,
    @ColumnInfo(name = "createdAt") // 생성 날짜
    val createdAt: Long,
    @ColumnInfo(name = "delivery_type") // 배달상태
    val deliveryType: Int = OrderDeliveryState.DELIVERING.type
)
