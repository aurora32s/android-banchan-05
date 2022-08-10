package com.seom.banchan.data.source.local

import com.seom.banchan.data.db.entity.CartMenuEntity
import com.seom.banchan.data.source.CartDataSource

class CartDataSourceImpl: CartDataSource {
    override suspend fun addCartItem(cartMenuItem: CartMenuEntity): Int {
        return 0
    }
}