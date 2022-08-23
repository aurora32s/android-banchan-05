package com.seom.banchan.worker.work

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.seom.banchan.domain.usecase.UpdateDeliveryStateUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

@HiltWorker
class DeliveryStateChangeWork @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted params: WorkerParameters,
    private val updateDeliveryStateUseCase: UpdateDeliveryStateUseCase
) : CoroutineWorker(context, params) {

    override suspend fun doWork(): Result = try {
        val orderId = inputData.getLong(KEY_ORDER_ID, -1)
        if (orderId < 0) throw Exception()

        updateDeliveryStateUseCase(orderId)
        Result.success()
    } catch (exception: Exception) {
        Result.failure()
    }

    companion object {
        const val KEY_ORDER_ID = "order_id"
    }
}