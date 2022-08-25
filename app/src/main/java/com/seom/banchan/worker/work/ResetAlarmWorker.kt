package com.seom.banchan.worker.work

import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.ForegroundInfo
import androidx.work.WorkerParameters
import com.seom.banchan.R
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
        setForeground(
            ForegroundInfo(
                1,
                NotificationCompat.Builder(context, "create")
                    .setSmallIcon(R.drawable.ic_app_icon_small)
                    .setContentTitle("배달이 도착했어요!")
                    .setContentText("hello world")
                    .setPriority(NotificationCompat.PRIORITY_HIGH)
                    .setAutoCancel(true)
                    .setDefaults(NotificationCompat.DEFAULT_ALL)
                    .apply {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            this.color = context.resources.getColor(R.color.primaryAccent, null)
                        }
                    }.build()
            )
        )
        getAllDeliveryAlarmInfoUseCase()
            .onSuccess { deliveries ->
                deliveries.forEach {
                    DeliveryAlarmManager.create(context, it)
                }
            }
            .onFailure {
                throw Exception("reset alarm exceptoin")
            }
        Result.success()
    } catch (exception: Exception) {
        Result.failure()
    }

    companion object {
        const val TAG = ".ResetAlarmWorker"
    }
}