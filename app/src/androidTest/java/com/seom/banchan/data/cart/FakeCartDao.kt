package com.seom.banchan.data.cart

import com.seom.banchan.data.db.dao.CartDao
import com.seom.banchan.data.db.entity.CartMenuEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeCartDao : CartDao {

    val cartData : HashMap<String,CartMenuEntity> = hashMapOf()

    override suspend fun insertOrReplaceCartMenu(cartMenuEntity: CartMenuEntity): Long {
        if(cartData.containsKey(cartMenuEntity.menuId))
            cartData[cartMenuEntity.menuId] = cartMenuEntity
        else
            cartData[cartMenuEntity.menuId] = cartMenuEntity
        return 1
    }

    override suspend fun getCartMenuById(menuId: String): CartMenuEntity? {
        return cartData.get(menuId)
    }

    override fun getCartMenus(): Flow<List<CartMenuEntity>> {
        return flow {
            emit(
                cartData.values.toList()
            )
        }
    }

    override suspend fun deleteCartMenuList(menuIds: List<String>): Int {
        menuIds.forEach {
            cartData.remove(it)
        }
        return 1
    }

    override suspend fun deleteCartMenu(menuId: String): Int {
        cartData.remove(menuId)
        return 1
    }

    override suspend fun updateCartMenuListSelected(menuIds: List<String>): Int {
        menuIds.forEach {
            cartData.set(
                it,cartData.getValue(it).copy(
                    selected = !cartData.getValue(it).selected
                )
            )
        }
        return 1
    }

    override suspend fun updateCartMenuSelected(menuId: String): Int {
        cartData.set(
            menuId,cartData.getValue(menuId).copy(
                selected = !cartData.getValue(menuId).selected
            )
        )
        return 1
    }

    override suspend fun updateCartMenuCountIncrease(menuId: String): Int {
        cartData.set(
            menuId,cartData.getValue(menuId).copy(
                count = cartData.getValue(menuId).count + 1
            )
        )
        return 1
    }

    override suspend fun updateCartMenuCountDecrease(menuId: String): Int {
        cartData.set(
            menuId,cartData.getValue(menuId).copy(
                count = cartData.getValue(menuId).count - 1
            )
        )
        return 1
    }

    override suspend fun updateCartMenuCount(menuId: String, count: Int): Int {
        cartData.set(
            menuId,cartData.getValue(menuId).copy(
                count = count
            )
        )
        return 1
    }
}