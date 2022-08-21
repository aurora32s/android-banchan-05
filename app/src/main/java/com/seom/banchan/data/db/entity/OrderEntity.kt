package com.seom.banchan.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.seom.banchan.domain.model.order.OrderModel
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
    @ColumnInfo(name = "delivery_state") // 배달상태
    val deliveryState: Int = OrderDeliveryState.DELIVERING.type,
    @ColumnInfo(name = "expected_time") // 배달 예상 시간
    val expectedTime: Long
)

fun OrderEntity.toModel() = OrderModel(
    orderId = orderId,
    createdAt = createdAt,
    deliveryState = OrderDeliveryState.getDeliveryType(deliveryState),
    expectedTime = expectedTime
)