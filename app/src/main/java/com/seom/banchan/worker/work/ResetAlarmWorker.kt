package com.seom.banchan.worker.work

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.seom.banchan.domain.usecase.GetAllDeliveryAlarmInfoUseCase
import com.seom.banchan.worker.alarm.DeliveryAlarmManager
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

@HiltWorker
class ResetAlarmWorker @AssistedInject constructor(
    @Assisted val context: Context,
    @Assisted params: WorkerParameters,
    private val getAllDeliveryAlarmInfoUseCase: GetAllDeliveryAlarmInfoUseCase
) : CoroutineWorker(context, params) {
    override suspend fun doWork(): Result = try {
        getAllDeliveryAlarmInfoUseCase()
            .onSuccess { deliveries ->
                deliveries.forEach {
                    DeliveryAlarmManager.create(context, it)
                }
            }
            .onFailure {
                throw Exception("reset alarm exception")
            }
        Result.success()
    } catch (exception: Exception) {
        Result.failure()
    }

    companion object {
        const val TAG = ".ResetAlarmWorker"
    }
}