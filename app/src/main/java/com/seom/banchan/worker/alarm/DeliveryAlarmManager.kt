package com.seom.banchan.worker.alarm

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.SystemClock
import com.seom.banchan.di.IODispatcher
import com.seom.banchan.domain.usecase.GetDeliveryAlarmInfoUseCase
import com.seom.banchan.ui.model.order.OrderListItemUiModel
import com.seom.banchan.worker.model.DeliveryAlarmModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

class DeliveryAlarmManager(
    private val getDeliveryAlarmInfoUseCase: GetDeliveryAlarmInfoUseCase
) {

    suspend fun create(context: Context, orderId: Long) {
        getDeliveryAlarmInfoUseCase(orderId)
            .onSuccess {
                val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

                val intent = Intent(context, DeliveryAlarmReceiver::class.java)
                intent.putExtra(DeliveryAlarmReceiver.KEY_ORDER_ITEM, it)

                val pendingIntent = PendingIntent.getBroadcast(
                    context,
                    it.orderId.toInt(),
                    intent,
                    PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
                )

                val triggerTime = SystemClock.elapsedRealtime() + it.expectedTime
                alarmManager.setExact(
                    AlarmManager.ELAPSED_REALTIME_WAKEUP, // 절전모드에서는 작동 안함
                    triggerTime,
                    pendingIntent
                )
            }
    }
}