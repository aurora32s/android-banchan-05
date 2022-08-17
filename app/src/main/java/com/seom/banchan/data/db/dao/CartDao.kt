package com.seom.banchan.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.seom.banchan.data.db.entity.CartMenuEntity

@Dao
interface CartDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCartMenu(cartMenuEntity: CartMenuEntity): Int
}