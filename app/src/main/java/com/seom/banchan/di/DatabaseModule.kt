package com.seom.banchan.di

import android.content.Context
import androidx.room.Room
import com.seom.banchan.data.db.BanchanDatabase
import com.seom.banchan.data.db.dao.RecentlyDao
import dagger.Binds
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
    fun provideDatabase(@ApplicationContext context : Context) : BanchanDatabase{
        return Room.databaseBuilder(
            context.applicationContext,
            BanchanDatabase::class.java,
            "BanchanDatabase"
        ).build()
    }

    @Provides
    fun provideRecentlyDao(database: BanchanDatabase) : RecentlyDao{
        return database.recentlyDao()
    }
}
