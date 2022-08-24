package com.seom.banchan.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey
import com.seom.banchan.domain.model.order.OrderItemModel

/**
 * 주문 내역에 담겨 있는 메뉴 아이템 Entity
 */
@Entity(
    tableName = "order_item_table",
    primaryKeys = ["menu_id", "order_id"],
    foreignKeys = [
        ForeignKey(
            entity = OrderEntity::class,
            parentColumns = ["order_id"],
            childColumns = ["order_id"],
            onDelete = CASCADE
        )
    ]
)
data class OrderItemEntity(
    @ColumnInfo(name = "menu_id") val menuId: String,
    @ColumnInfo(name = "order_id") val orderId: Long,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "image") val image: String?,
    @ColumnInfo(name = "sale_price") val salePrice: Int,
    @ColumnInfo(name = "count") val count: Int
)

fun OrderItemEntity.toModel() = OrderItemModel(
    menuId = menuId,
    orderId = orderId,
    name = name,
    image = image,
    salePrice = salePrice,
    count = count
)

fun OrderItemModel.toEntity(orderId: Long) = OrderItemEntity(
    menuId = menuId,
    orderId = orderId,
    name = name,
    image = image,
    salePrice = salePrice,
    count = count
)