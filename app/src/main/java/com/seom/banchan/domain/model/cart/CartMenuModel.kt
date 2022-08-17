package com.seom.banchan.domain.model.cart

import com.seom.banchan.data.db.entity.CartMenuEntity

/**
 * 장바구니에 담겨있는 메뉴의 domain model
 */
data class CartMenuModel(
    val menuId: String, // 메뉴의 id
    val name: String, // 메뉴 이름
    val image: String?, // 메뉴 이미지
    val salePrice: Int, // 메뉴 판매가
    val count: Int // 메뉴 개수
)

fun CartMenuModel.toEntity(originCount: Int = 0) = CartMenuEntity(
    menuId = menuId,
    name = name,
    image = image,
    salePrice = salePrice,
    count = count + originCount
)
