package com.seom.banchan.data.db.dao

import android.database.sqlite.SQLiteConstraintException
import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.seom.banchan.data.db.entity.RecentEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RecentDao {

    @Query("SELECT * FROM recent_table ORDER BY recent_time DESC")
    fun getRecents() : Flow<List<RecentEntity>>

    @Query("SELECT * FROM recent_table ORDER BY recent_time DESC LIMIT 7")
    fun getLatestRecents() : Flow<List<RecentEntity>>

    @Query("SELECT * FROM recent_table ORDER BY recent_time DESC")
    fun getRecentPaging() : PagingSource<Int,RecentEntity>

    @Insert
    suspend fun insertRecent(recentEntity: RecentEntity)

    @Update
    suspend fun updateRecent(recentEntity: RecentEntity)

    suspend fun upsertRecent(recentEntity: RecentEntity){
        try {
            insertRecent(recentEntity)
        } catch (e : SQLiteConstraintException){
            updateRecent(recentEntity)
        }
    }
}