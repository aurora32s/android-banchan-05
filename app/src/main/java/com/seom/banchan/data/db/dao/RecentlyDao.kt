package com.seom.banchan.data.db.dao

import android.database.sqlite.SQLiteConstraintException
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.seom.banchan.data.db.entity.RecentlyEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RecentlyDao {

    @Query("SELECT * FROM recently_table ORDER BY recently_time DESC")
    fun getRecentlys() : Flow<List<RecentlyEntity>>

    @Insert
    suspend fun insertRecently(recentlyEntity: RecentlyEntity)

    @Update
    suspend fun updateRecently(recentlyEntity: RecentlyEntity)

    suspend fun upsertRecently(recentlyEntity: RecentlyEntity){
        try {
            insertRecently(recentlyEntity)
        } catch (e : SQLiteConstraintException){
            updateRecently(recentlyEntity)
        }
    }
}