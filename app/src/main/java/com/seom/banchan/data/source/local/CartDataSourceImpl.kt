package com.seom.banchan.data.source.local

import com.seom.banchan.data.db.dao.CartDao
import com.seom.banchan.data.db.entity.CartMenuEntity
import com.seom.banchan.data.source.CartDataSource
import com.seom.banchan.domain.model.cart.CartMenuModel
import com.seom.banchan.domain.model.cart.toEntity
import javax.inject.Inject

class CartDataSourceImpl @Inject constructor(
    private val cartDao: CartDao
) : CartDataSource {

    // 장바구니에 메뉴 추가
    override suspend fun addCartItem(cartMenuItem: CartMenuModel): Result<Long>
        = try {
            val id = cartDao.insertCartMenu(cartMenuItem.toEntity())
            Result.success(id)
        } catch (exception: Exception) {
            Result.failure(exception)
        }
}