package com.seom.banchan.domain.repository

import com.seom.banchan.domain.model.cart.CartMenuModel
import kotlinx.coroutines.flow.Flow

interface CartRepository {
    /**
     * 장바구니에 메뉴 추가, 동일한 메뉴가 있을 시 개수를 더하고 변경
     * */
    suspend fun addOrUpdateMenuToCart(menu: CartMenuModel): Result<Long>

    /**
     * 장바구니에 메뉴 추가, 동일한 메뉴가 있을 시 새로운 개수로 변경
     */
    suspend fun addOrReplaceMenuToCart(menu: CartMenuModel): Result<Long>

    /**
     * 장바구니에 있는 메뉴들의 id 리스트 요청
     */
    fun getCartMenusId(): Flow<List<CartMenuModel>>

    /*
    장바구니 메뉴 삭제
     */
    suspend fun deleteCartMenuList(menuIds: List<String>) : Int

    /*
    장바구니 선택한 메뉴 삭제
     */
    suspend fun deleteCartMenu(menuId: String) : Int

    /*
    장바구니 모든 메뉴 선택 상태 변경
     */
    suspend fun updateCartMenuListSelected(menuIds: List<String>) : Int

    /*
    장바구니 해당 메뉴 선택 상태 변경
     */
    suspend fun updateCartMenuSelected(menuId: String) : Int

    /*
    장바구니 해당 메뉴 개수 증가
     */
    suspend fun updateCartMenuCountIncrease(menuId: String) : Int

    /*
    장바구니 해당 메뉴 개수 감소
     */
    suspend fun updateCartMenuCountDecrease(menuId: String) : Int
}