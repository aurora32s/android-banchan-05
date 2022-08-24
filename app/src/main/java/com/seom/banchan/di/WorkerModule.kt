package com.seom.banchan.di

import com.seom.banchan.domain.usecase.GetDeliveryAlarmInfoUseCase
import com.seom.banchan.worker.alarm.DeliveryAlarmManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(FragmentComponent::class)
object WorkerModule {
    @Provides
    fun provideDeliveryAlarmManager(
        getDeliveryAlarmInfoUseCase: GetDeliveryAlarmInfoUseCase
    ) = DeliveryAlarmManager(
        getDeliveryAlarmInfoUseCase
    )
}