package com.seom.banchan.di

import com.seom.banchan.data.repository.CartRepositoryImpl
import com.seom.banchan.data.repository.MenuRepositoryImpl
import com.seom.banchan.domain.repository.CartRepository
import com.seom.banchan.domain.repository.MenuRepository
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
    abstract fun bindCartRepository(
        cartRepositoryImpl: CartRepositoryImpl
    ): CartRepository
}