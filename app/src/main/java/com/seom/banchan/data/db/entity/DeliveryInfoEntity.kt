package com.seom.banchan.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.seom.banchan.worker.model.DeliveryAlarmModel

@Entity
data class DeliveryInfoEntity(
    @ColumnInfo(name = "order_id") val orderId: Long,
    @ColumnInfo(name = "menu_name") val menuName: String,
    @ColumnInfo(name = "total_count") val totalCount: Int,
    @ColumnInfo(name = "expected_time") val expectedTime: Long,
    @ColumnInfo(name = "createdAt") val createdAt: Long
)

fun DeliveryInfoEntity.toModel() = DeliveryAlarmModel(
    orderId = orderId,
    menuName = menuName,
    totalCount = totalCount,
    expectedTime = expectedTime,
    createdAt = createdAt
)