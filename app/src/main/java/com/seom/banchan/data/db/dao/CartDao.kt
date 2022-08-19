package com.seom.banchan.data.db.dao

import androidx.room.*
import com.seom.banchan.data.db.entity.CartMenuEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CartDao {

    /**
     * 장바구니에 추가하거나 이미 있는 경우에는 교체할 경우 사용
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrReplaceCartMenu(cartMenuEntity: CartMenuEntity): Long

    @Query("SELECT * FROM cart_table WHERE menu_id = :menuId")
    suspend fun getCartMenuById(menuId: String): CartMenuEntity?

    @Query("SELECT * FROM cart_table WHERE menu_id = :menuId")
    fun getCartMenuByIdTest(menuId: String): Flow<CartMenuEntity>

    /**
     * 장바구니에 속한 모든 메뉴 리스트 요청
     */
    @Query("SELECT * FROM cart_table")
    fun getCartMenusId(): Flow<List<CartMenuEntity>>
}