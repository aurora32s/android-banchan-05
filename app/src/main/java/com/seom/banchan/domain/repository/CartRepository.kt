package com.seom.banchan.domain.repository

import com.seom.banchan.domain.model.cart.CartMenuModel
import kotlinx.coroutines.flow.Flow

interface CartRepository {
    /**
     * 장바구니에 메뉴 추가, 동일한 메뉴가 있을 시 개수를 더하고 변경
     * */
    suspend fun addOrUpdateMenuToCart(menu: CartMenuModel): Result<Long>

    /**
     * 장바구니에 메뉴 추가, 동일한 메뉴가 있을 시 새로운 개수로 변경
     */
    suspend fun addOrReplaceMenuToCart(menu: CartMenuModel): Result<Long>

    /**
     * 장바구니에 있는 메뉴들의 id 리스트 요청
     */
    suspend fun getCartMenusId(): Flow<List<Long>>
}