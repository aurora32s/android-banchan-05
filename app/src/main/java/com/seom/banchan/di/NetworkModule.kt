package com.seom.banchan.di

import com.seom.banchan.BuildConfig
import com.seom.banchan.data.api.MenuApiService
import com.seom.banchan.data.source.MenuDataSource
import com.seom.banchan.data.source.remote.MenuDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit

// API
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    fun provideMenuApiService(
        retrofit: Retrofit
    ) = retrofit.create(MenuApiService::class.java)
}


// Retrofit Builder
@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        converter: Converter.Factory
    ): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(converter)
            .baseUrl("")
            .build()
    }
}

// Factory
@Module
@InstallIn(SingletonComponent::class)
object ConvertFactoryModule {
    @Provides
    fun provideConvertFactory(): Converter.Factory {
        return GsonConverterFactory.create()
    }
}

// Client
@Module
@InstallIn(SingletonComponent::class)
object HttpClientModule {
    @Provides
    fun provideHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }
        return OkHttpClient
            .Builder()
            .connectTimeout(5, TimeUnit.SECONDS)
            .addInterceptor(interceptor)
            .build()
    }
}