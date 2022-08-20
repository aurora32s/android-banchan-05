package com.seom.banchan.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * 주문 내역이 담겨 있는 table
 */
@Entity(tableName = "order_table")
data class OrderEntity(
    @PrimaryKey
    @ColumnInfo(name = "order_id") val orderId: Long? = null, // order id
    @ColumnInfo(name = "createdAt") val createdAt: Long, // 생성 날짜
    @ColumnInfo(name = "completed") val completed: Boolean = false // 배달완료 여부
)
