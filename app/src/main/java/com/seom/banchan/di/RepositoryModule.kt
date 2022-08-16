package com.seom.banchan.di

import com.seom.banchan.data.repository.MenuRepositoryImpl
import com.seom.banchan.data.repository.RecentlyRepositoryImpl
import com.seom.banchan.domain.repository.MenuRepository
import com.seom.banchan.domain.repository.RecentlyRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindMenuRepository(
        menuRepositoryImpl: MenuRepositoryImpl
    ): MenuRepository

    @Binds
    abstract fun bindRecentlyRepository(
        recentlyRepositoryImpl: RecentlyRepositoryImpl
    ) : RecentlyRepository
}