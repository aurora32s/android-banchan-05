package com.seom.banchan.data.repository

import com.seom.banchan.data.source.CartDataSource
import com.seom.banchan.domain.model.cart.CartMenuModel
import com.seom.banchan.domain.repository.CartRepository
import javax.inject.Inject

class CartRepositoryImpl @Inject constructor(
    private val cartDataSource: CartDataSource
) : CartRepository {

    // 장바구니에 메뉴 추가
    override suspend fun addMenuToCart(menu: CartMenuModel): Result<Long> {
        return cartDataSource.addCartItem(menu)
    }
}