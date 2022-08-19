package com.seom.banchan.ui.model.order

import com.seom.banchan.ui.model.CellType
import com.seom.banchan.ui.model.Model

/**
 * 주문 내역 리스트의 주문 아이템 model
 */
data class OrderListItemModel(
    override val id: String = "Order",
    override val type: CellType = CellType.ORDER_LIST_ITEM,
    val orderId: Long, // 주문 내역 DB id
    val image: String?, // 주문 내역 대표 이미지
    val totalPrice: Int, // 총 주문 금액
    val menuCount: Int, // 해당 주문에 포함된 메뉴의 개수
    val deliveryCompleted: OrderDeliveryState // 배송 전, 배송중, 배송완료
) : Model(id, type)
