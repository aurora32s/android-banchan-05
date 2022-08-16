package com.seom.banchan.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.seom.banchan.data.db.dao.RecentlyDao
import com.seom.banchan.data.db.entity.RecentlyEntity

@Database(entities = [RecentlyEntity::class], version = 1, exportSchema = false)
abstract class BanchanDatabase : RoomDatabase() {
    abstract fun recentlyDao() : RecentlyDao
}