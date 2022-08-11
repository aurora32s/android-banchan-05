package com.seom.banchan.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import javax.inject.Qualifier

@Qualifier
annotation class IODispatcher

@Module
@InstallIn(SingletonComponent::class)
object CoroutineModule {
    @IODispatcher
    @Provides
    fun provideIODispatcher() = Dispatchers.IO
}