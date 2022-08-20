package com.seom.banchan.domain.model.order

import com.seom.banchan.ui.model.order.OrderDeliveryState
import com.seom.banchan.ui.model.order.OrderListItemModel

data class OrderListModel(
    val orderId: Long, // 주문 번호
    val menuName: String, // 대표 메뉴 이름
    val image: String?, // 대표 이미지 이름
    val totalPrice: Int, // 총 주문 금액
    val menuCount: Int, // 총 주문 메뉴 개수
    val deliveryType: OrderDeliveryState // 배달 상태
)

fun OrderListModel.toUiModel() = OrderListItemModel(
    orderId = orderId,
    menuName = menuName,
    image = image,
    totalPrice = totalPrice,
    menuCount = menuCount,
    deliveryCompleted = deliveryType
)