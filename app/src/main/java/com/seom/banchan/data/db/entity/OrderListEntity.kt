package com.seom.banchan.data.db.entity

import androidx.room.ColumnInfo
import com.seom.banchan.domain.model.order.OrderListModel
import com.seom.banchan.ui.model.order.OrderDeliveryState

/**
 * 주문 내역 리스트에서 필요한 entity
 */
data class OrderListEntity(
    @ColumnInfo(name = "order_id") val orderId: Long, // 주문 번호
    @ColumnInfo(name = "menu_name") val menuName: String, // 대표 메뉴 번호
    @ColumnInfo(name = "image") val image: String?, // 대표 메뉴 이미지
    @ColumnInfo(name = "total_price") val totalPrice: Int, // 총 주문 금액
    @ColumnInfo(name = "menu_count") val menuCount: Int, // 해당 주문에 포함된 메뉴 개수
    @ColumnInfo(name = "delivery_type") val deliveryType: Int // 배송 완료 여부(0: 배송중, 1: 배송완료)
)

fun OrderListEntity.toModel() = OrderListModel(
    orderId = orderId,
    menuName = menuName,
    image = image,
    totalPrice = totalPrice,
    menuCount = menuCount,
    deliveryType = OrderDeliveryState.getDeliveryType(deliveryType)
)