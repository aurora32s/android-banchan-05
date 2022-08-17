package com.seom.banchan.data.source.local

import com.seom.banchan.data.db.entity.CartMenuEntity
import com.seom.banchan.data.source.CartDataSource
import com.seom.banchan.domain.model.cart.CartMenuModel

class CartDataSourceImpl: CartDataSource {

    // 장바구니에 메뉴 추가
    override suspend fun addCartItem(cartMenuItem: CartMenuModel): Result<Int> {
        TODO("Not yet implemented")
    }
}