package com.seom.banchan.data.db.dao

import androidx.room.*
import com.seom.banchan.data.db.entity.CartMenuEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

@Dao
interface CartDao {

    /**
     * 장바구니에 추가하거나 이미 있는 경우에는 교체할 경우 사용
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrReplaceCartMenu(cartMenuEntity: CartMenuEntity): Long

    @Query("SELECT * FROM cart_table WHERE menu_id = :menuId")
    suspend fun getCartMenuById(menuId: String): CartMenuEntity?

    /**
     * 장바구니에 속한 모든 메뉴 리스트 요청
     */
    @Query("SELECT * FROM cart_table")
    fun getCartMenus(): Flow<List<CartMenuEntity>>

    /*
    장바구니 메뉴 삭제
     */
    @Query ("DELETE FROM cart_table WHERE menu_id IN (:menuIds)")
    suspend fun deleteCartMenuList(menuIds: List<String>) : Int

    /*
    장바구니 선택한 메뉴 삭제
     */
    @Query ("DELETE FROM cart_table WHERE menu_id = :menuId")
    suspend fun deleteCartMenu(menuId: String) : Int

    /*
    장바구니 모든 메뉴 선택 상태 변경
     */
    @Query ("UPDATE cart_table SET selected = NOT selected WHERE menu_id IN (:menuIds)")
    suspend fun updateCartMenuListSelected(menuIds: List<String>) : Int

    /*
    장바구니 해당 메뉴 선택 상태 변경
     */
    @Query ("UPDATE cart_table SET selected = NOT selected WHERE menu_id = :menuId")
    suspend fun updateCartMenuSelected(menuId: String) : Int

    /*
    장바구니 해당 메뉴 개수 증가
     */
    @Query ("UPDATE cart_table SET count = count + 1 WHERE menu_id = :menuId")
    suspend fun updateCartMenuCountIncrease(menuId: String) : Int

    /*
    장바구니 해당 메뉴 개수 감소
     */
    @Query ("UPDATE cart_table SET count = count - 1 WHERE menu_id = :menuId")
    suspend fun updateCartMenuCountDecrease(menuId: String) : Int

    /*
    장바구니 해당 메뉴 개수 값 변경
     */
    @Query ("UPDATE cart_table SET count = :count WHERE menu_id = :menuId")
    suspend fun updateCartMenuCount(menuId: String,count : Int) : Int


}