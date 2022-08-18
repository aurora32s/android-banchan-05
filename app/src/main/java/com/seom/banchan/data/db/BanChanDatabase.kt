package com.seom.banchan.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.seom.banchan.data.db.dao.CartDao
import com.seom.banchan.data.db.dao.RecentlyDao
import com.seom.banchan.data.db.entity.CartMenuEntity
import com.seom.banchan.data.db.entity.RecentlyEntity

@Database(
    entities = [CartMenuEntity::class, RecentlyEntity::class],
    version = BanChanDatabase.DATABASE_VERSION,
    exportSchema = false
)
abstract class BanChanDatabase : RoomDatabase() {

    abstract fun cartDao(): CartDao
    abstract fun recentlyDao(): RecentlyDao

    companion object {
        const val DATABASE_NAME = "BanChan.db"
        const val DATABASE_VERSION = 1
    }
}