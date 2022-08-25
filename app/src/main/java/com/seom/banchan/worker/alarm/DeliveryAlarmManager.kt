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

object DeliveryAlarmManager {

    fun create(context: Context, deliveryAlarmModel: DeliveryAlarmModel) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        val intent = Intent(context, DeliveryAlarmReceiver::class.java)
        intent.putExtra(DeliveryAlarmReceiver.KEY_ORDER_ITEM, deliveryAlarmModel)

        val pendingIntent = PendingIntent.getBroadcast(
            context,
            deliveryAlarmModel.orderId.toInt(),
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val triggerTime = SystemClock.elapsedRealtime() + deliveryAlarmModel.expectedTime
        alarmManager.setExact(
            AlarmManager.ELAPSED_REALTIME_WAKEUP, // 절전모드에서는 작동 안함
            triggerTime,
            pendingIntent
        )
    }
}