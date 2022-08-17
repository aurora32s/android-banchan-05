package com.seom.banchan.domain.repository

import com.seom.banchan.domain.model.cart.CartMenuModel

interface CartRepository {
    /**
     * 장바구니에 메뉴 추가
     */
    suspend fun addMenuToCart(menu: CartMenuModel): Result<Int>
}