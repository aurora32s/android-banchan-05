package com.seom.banchan.worker.work

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.ForegroundInfo
import androidx.work.WorkerParameters
import com.seom.banchan.domain.usecase.GetDeliveryAlarmInfoUseCase
import com.seom.banchan.domain.usecase.UpdateDeliveryStateUseCase
import com.seom.banchan.worker.notification.DeliveryNotificationManager
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

@HiltWorker
class DeliveryStateChangeWork @AssistedInject constructor(
    @Assisted val context: Context,
    @Assisted params: WorkerParameters,
    private val updateDeliveryStateUseCase: UpdateDeliveryStateUseCase,
    private val getDeliveryAlarmInfoUseCase: GetDeliveryAlarmInfoUseCase
) : CoroutineWorker(context, params) {

    override suspend fun doWork(): Result = try {
        val orderId = inputData.getLong(KEY_ORDER_ID, -1)
        if (orderId < 0) throw Exception()

        // 해당 주문 배달 완료 setting
        updateDeliveryStateUseCase(orderId)
            .onSuccess {
                // 해당 주문에 대해 Notification 알림
                getDeliveryAlarmInfoUseCase(orderId)
                    .onSuccess {
                        DeliveryNotificationManager.create(context = context, it)
                    }
            }
        Result.success()
    } catch (exception: Exception) {
        Result.failure()
    }

    companion object {
        const val KEY_ORDER_ID = "order_id"
    }
}