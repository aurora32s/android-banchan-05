package com.seom.banchan.data.source

import com.seom.banchan.data.db.entity.CartMenuEntity
import com.seom.banchan.domain.model.cart.CartMenuModel

/**
 * 장바구니 아이템과 관련된 data source
 */
interface CartDataSource {
    /**
     * 만약 동일한 아이템이 있는 경우, 개수가 더해진 새로운 메뉴 아이템으로 변경
     */
    suspend fun addOrReplaceMenuToCart(cartMenuItem: CartMenuModel): Result<Long>
}