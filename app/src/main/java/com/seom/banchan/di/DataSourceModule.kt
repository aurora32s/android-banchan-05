package com.seom.banchan.di

import com.seom.banchan.data.source.MenuDataSource
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
}