package com.seom.banchan.data.source.local

import com.seom.banchan.data.db.dao.CartDao
import com.seom.banchan.data.db.entity.toModel
import com.seom.banchan.data.source.CartDataSource
import com.seom.banchan.domain.model.cart.CartMenuModel
import com.seom.banchan.domain.model.cart.toEntity
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class CartDataSourceImpl @Inject constructor(
    private val cartDao: CartDao
) : CartDataSource {

    // 장바구니에 메뉴를 추가하거나 이미 있다면 개수를 더해서 새로 추가
    override suspend fun addOrUpdateMenuToCart(cartMenuItem: CartMenuModel): Result<Long> = try {
        val cartMenu = cartDao.getCartMenuById(cartMenuItem.menuId)
        val originCount = cartMenu?.count ?: 0
        val result = cartDao.insertOrReplaceCartMenu(cartMenuItem.toEntity(originCount))

        Result.success(result)
    } catch (exception: Exception) {
        Result.failure(exception)
    }

    override suspend fun addOrReplaceMenuToCart(cartMenuItem: CartMenuModel): Result<Long> = try {
        val result = cartDao.insertOrReplaceCartMenu(cartMenuItem.toEntity(originCount = 0))
        Result.success(result)
    } catch (exception: Exception) {
        Result.failure(exception)
    }

    // 장바구니에 속한 메뉴들의 id 리스트 요청
    override fun getCartMenusId(): Flow<List<CartMenuModel>> = flow {
        cartDao.getCartMenus().collect {
            emit(it.map { it.toModel() })
        }
    }

    override suspend fun deleteCartMenuList(menuIds: List<String>): Int {
        return cartDao.deleteCartMenuList(menuIds)
    }

    override suspend fun deleteCartMenu(menuId: String): Int {
        return cartDao.deleteCartMenu(menuId)
    }

    override suspend fun updateCartMenuListSelected(menuIds: List<String>): Int {
        return cartDao.updateCartMenuListSelected(menuIds)
    }

    override suspend fun updateCartMenuSelected(menuId: String): Int {
        return cartDao.updateCartMenuSelected(menuId)
    }

    override suspend fun updateCartMenuCountIncrease(menuId: String): Int {
        return cartDao.updateCartMenuCountIncrease(menuId)
    }

    override suspend fun updateCartMenuCountDecrease(menuId: String): Int {
        return cartDao.updateCartMenuCountDecrease(menuId)
    }

    override suspend fun updateCartMenuCount(menuId: String, count: Int): Int {
        return cartDao.updateCartMenuCount(menuId, count)
    }
}