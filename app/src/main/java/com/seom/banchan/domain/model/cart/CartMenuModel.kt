package com.seom.banchan.domain.model.cart

/**
 * 장바구니에 담겨있는 메뉴의 domain model
 */
data class CartMenuModel(
    val id: Int = -1, // 장바구니에서의 id
    val menuId: String, // 메뉴의 id
    val name: String, // 메뉴 이름
    val image: String?, // 메뉴 이미지
    val salePrice: Int, // 메뉴 판매가
    val count: Int // 메뉴 개수
)
