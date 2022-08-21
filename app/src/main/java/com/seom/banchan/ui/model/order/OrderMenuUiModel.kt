package com.seom.banchan.ui.model.order

import com.seom.banchan.ui.model.CellType
import com.seom.banchan.ui.model.Model

/**
 * 주문 상세 화면에 보이는 메뉴의 UI Model
 */
data class OrderMenuUiModel(
    override val id: String = "orderMenu",
    override val type: CellType = CellType.ORDER_MENU_CELL,
    val menuName: String, // 메뉴 이름
    val image: String?, // 메뉴 이미지
    val count: Int, // 메뉴가 담긴 개수
    val salePrice: Int // 메뉴 판매가
) : Model(id, type)
