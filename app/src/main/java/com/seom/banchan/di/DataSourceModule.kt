package com.seom.banchan.di

import com.seom.banchan.data.source.CartDataSource
import com.seom.banchan.data.source.MenuDataSource
import com.seom.banchan.data.source.OrderDataSource
import com.seom.banchan.data.source.local.CartDataSourceImpl
import com.seom.banchan.data.source.RecentDataSource
import com.seom.banchan.data.source.local.OrderDataSourceImpl
import com.seom.banchan.data.source.local.RecentDataSourceImpl
import com.seom.banchan.data.source.remote.MenuDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {
    @Binds
    abstract fun bindMenuDataSource(
        menuDataSource: MenuDataSourceImpl
    ): MenuDataSource

    @Binds
    abstract fun bindCartDataSource(
        cartDataSource: CartDataSourceImpl
    ): CartDataSource

    @Binds
    abstract fun bindRecentDataSource(
        recentDataSourceImpl: RecentDataSourceImpl
    ): RecentDataSource

    @Binds
    abstract fun bindOrderDataSource(
        orderDataSource: OrderDataSourceImpl
    ): OrderDataSource
}