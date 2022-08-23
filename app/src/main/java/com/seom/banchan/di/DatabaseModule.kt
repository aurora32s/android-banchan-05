package com.seom.banchan.di

import android.content.Context
import androidx.room.Room
import com.seom.banchan.data.db.BanChanDatabase
import com.seom.banchan.data.db.MIGRATION_1_2
import com.seom.banchan.data.db.dao.OrderDao
import com.seom.banchan.data.db.dao.RecentDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Data layer 단과 관련된 DI 모듈
 */
@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context
    ): BanChanDatabase {
        return Room.databaseBuilder(
            context,
            BanChanDatabase::class.java,
            BanChanDatabase.DATABASE_NAME
        ).fallbackToDestructiveMigration() // Migration을 찾지 못하여 crash 발생 시 db 재생성, 데이터 모두 유실
            // .addMigrations(MIGRATION_1_2) Migration 추가
            .build()
    }
}

@Module
@InstallIn(SingletonComponent::class)
object DaoModule {
    @Provides
    fun provideCartDao(
        database: BanChanDatabase
    ) = database.cartDao()

    @Singleton
    @Provides
    fun provideRecentDao(
        database: BanChanDatabase
    ): RecentDao {
        return database.recentDao()
    }

    @Singleton
    @Provides
    fun providesOrderDao(
        database: BanChanDatabase
    ): OrderDao {
        return database.orderDao()
    }
}