package com.seom.banchan.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.seom.banchan.data.db.dao.CartDao
import com.seom.banchan.data.db.entity.CartMenuEntity

@Database(
    entities = [CartMenuEntity::class],
    version = BanChanDatabase.DATABASE_VERSION,
    exportSchema = false
)
abstract class BanChanDatabase : RoomDatabase() {

    abstract fun cartDao(): CartDao

    companion object {
        const val DATABASE_NAME = "BanChan.db"
        const val DATABASE_VERSION = 1
    }
}