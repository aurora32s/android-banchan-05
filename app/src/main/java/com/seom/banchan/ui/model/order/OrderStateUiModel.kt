package com.seom.banchan.ui.model.order

import com.seom.banchan.ui.model.CellType
import com.seom.banchan.ui.model.Model

/**
 * 주문 상세 화면 상단 배달 상태
 */
data class OrderStateUiModel(
    override val id: String = "orderState",
    override val type: CellType = CellType.ORDER_STATE_CELL,
    val orderDeliveryState: OrderDeliveryState, // 배달 상태
    val createdAt: Long, // 배달이 시작된 시간
    val expectedDeliveryTime: Long, // 예상 배달 소요 시간
    val menuCount: Int // 메뉴 개수
): Model(id, type)
