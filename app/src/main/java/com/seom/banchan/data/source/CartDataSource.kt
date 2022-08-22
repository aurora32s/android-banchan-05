package com.seom.banchan.data.source

import androidx.room.Query
import com.seom.banchan.data.db.entity.CartMenuEntity
import com.seom.banchan.domain.model.cart.CartMenuModel
import kotlinx.coroutines.flow.Flow

/**
 * 장바구니 아이템과 관련된 data source
 */
interface CartDataSource {
    /**
     * 만약 동일한 아이템이 있는 경우, 개수가 더해진 새로운 메뉴 아이템으로 변경
     */
    suspend fun addOrUpdateMenuToCart(cartMenuItem: CartMenuModel): Result<Long>

    /**
     * 만약 동일한 아이템이 있는 경우, 새로운 개수로 메뉴 아이템 변경
     */
    suspend fun addOrReplaceMenuToCart(cartMenuItem: CartMenuModel): Result<Long>

    /**
     * 현재 장바구니에 있는 메뉴들의 id 리스트 요청
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

    /*
    장바구니 해당 메뉴 개수 값 변경
     */
    suspend fun updateCartMenuCount(menuId: String,count : Int) : Int
}