package com.seom.banchan.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.seom.banchan.data.db.dao.CartDao
import com.seom.banchan.data.db.dao.OrderDao
import com.seom.banchan.data.db.dao.RecentDao
import com.seom.banchan.data.db.entity.CartMenuEntity
import com.seom.banchan.data.db.entity.OrderEntity
import com.seom.banchan.data.db.entity.OrderItemEntity
import com.seom.banchan.data.db.entity.RecentEntity

@Database(
    entities = [
        CartMenuEntity::class,
        RecentEntity::class,
        OrderEntity::class,
        OrderItemEntity::class
    ],
    version = BanChanDatabase.DATABASE_VERSION,
    exportSchema = false
)
abstract class BanChanDatabase : RoomDatabase() {

    abstract fun cartDao(): CartDao
    abstract fun recentDao(): RecentDao
    abstract fun orderDao(): OrderDao

    companion object {
        const val DATABASE_NAME = "BanChan.db"
        const val DATABASE_VERSION = 1
    }
}