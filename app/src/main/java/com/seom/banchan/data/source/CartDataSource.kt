package com.seom.banchan.data.source

import com.seom.banchan.data.db.entity.CartMenuEntity

/**
 * 장바구니 아이템과 관련된 data source
 */
interface CartDataSource {
    suspend fun addCartItem(cartMenuItem: CartMenuEntity): Int
}