package com.seom.banchan.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.seom.banchan.data.db.BanChanDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

/**
 * Data layer 단과 관련된 DI 모듈
 */
@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context
    ): BanChanDatabase {
        return Room.databaseBuilder(
            context,
            BanChanDatabase::class.java,
            BanChanDatabase.DATABASE_NAME
        ).build()
    }
}

@Module
@InstallIn(SingletonComponent::class)
object DaoModule {
    @Provides
    fun provideCartDao(
        database: BanChanDatabase
    ) = database.cartDao()
}