package com.seom.banchan.data.source

import com.seom.banchan.data.db.entity.CartMenuEntity
import com.seom.banchan.domain.model.cart.CartMenuModel

/**
 * 장바구니 아이템과 관련된 data source
 */
interface CartDataSource {
    /**
     * 장바구니에 새로운 아이템 추가
     */
    suspend fun addCartItem(cartMenuItem: CartMenuModel): Result<Int>
}