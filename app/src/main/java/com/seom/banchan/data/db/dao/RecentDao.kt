package com.seom.banchan.data.db.dao

import android.database.sqlite.SQLiteConstraintException
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