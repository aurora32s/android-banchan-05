package com.seom.banchan.data.repository

import com.seom.banchan.data.source.CartDataSource
import com.seom.banchan.domain.model.cart.CartMenuModel
import com.seom.banchan.domain.repository.CartRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CartRepositoryImpl @Inject constructor(
    private val cartDataSource: CartDataSource
) : CartRepository {

    // 장바구니에 메뉴 추가
    override suspend fun addOrUpdateMenuToCart(menu: CartMenuModel): Result<Long> {
        return cartDataSource.addOrUpdateMenuToCart(menu)
    }

    override suspend fun addOrReplaceMenuToCart(menu: CartMenuModel): Result<Long> {
        return cartDataSource.addOrReplaceMenuToCart(menu)
    }

    // 장바구니에 있는 메뉴들의 id 리스트 요청
    override fun getCartMenusId(): Flow<List<CartMenuModel>> {
        return cartDataSource.getCartMenusId()
    }

    override suspend fun deleteCartMenuList(menuIds: List<String>): Int {
        return cartDataSource.deleteCartMenuList(menuIds)
    }

    override suspend fun deleteCartMenu(menuId: String): Int {
        return cartDataSource.deleteCartMenu(menuId)
    }

    override suspend fun updateCartMenuListSelected(menuIds: List<String>): Int {
        return cartDataSource.updateCartMenuListSelected(menuIds)
    }

    override suspend fun updateCartMenuSelected(menuId: String): Int {
        return cartDataSource.updateCartMenuSelected(menuId)
    }

    override suspend fun updateCartMenuCountIncrease(menuId: String): Int {
        return cartDataSource.updateCartMenuCountIncrease(menuId)
    }

    override suspend fun updateCartMenuCountDecrease(menuId: String): Int {
        return cartDataSource.updateCartMenuCountDecrease(menuId)
    }

    override suspend fun updateCartMenuCount(menuId: String, count: Int): Int {
        return cartDataSource.updateCartMenuCount(menuId, count)
    }
}