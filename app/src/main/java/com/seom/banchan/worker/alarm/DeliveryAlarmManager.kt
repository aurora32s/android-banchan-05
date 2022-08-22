package com.seom.banchan.worker.alarm

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.SystemClock
import com.seom.banchan.ui.model.order.OrderListItemUiModel
import com.seom.banchan.worker.model.DeliveryAlarmModel

object DeliveryAlarmManager {

    fun create(context: Context, alarmModel: DeliveryAlarmModel) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        val intent = Intent(context, DeliveryAlarmReceiver::class.java)
        intent.putExtra(DeliveryAlarmReceiver.KEY_ORDER_ITEM, alarmModel)

        val pendingIntent = PendingIntent.getBroadcast(
            context,
            alarmModel.orderId.toInt(),
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val triggerTime = SystemClock.elapsedRealtime() + alarmModel.expectedTime
        alarmManager.set(
            AlarmManager.ELAPSED_REALTIME, // 절전모드에서는 작동 안함
            triggerTime,
            pendingIntent
        )
    }
}