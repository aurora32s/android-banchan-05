package com.seom.banchan.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.seom.banchan.data.db.entity.CartMenuEntity

@Dao
interface CartDao {

    /**
     * 장바구니에 추가하거나 이미 있는 경우에는 교체할 경우 사용
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrReplaceCartMenu(cartMenuEntity: CartMenuEntity): Long

    @Query("SELECT * FROM cart_table WHERE menu_id = :menuId")
    suspend fun getCartMenuById(menuId: String): CartMenuEntity?
}