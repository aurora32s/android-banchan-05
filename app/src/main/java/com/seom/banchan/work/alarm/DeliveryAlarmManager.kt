package com.seom.banchan.work.alarm

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.SystemClock
import com.seom.banchan.ui.model.order.OrderListItemUiModel

object DeliveryAlarmManager {

    fun create(context: Context, orderItem: OrderListItemUiModel, deliveryTime: Long) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        val intent = Intent(context, DeliveryAlarmReceiver::class.java)
        intent.putExtra(DeliveryAlarmReceiver.KEY_ORDER_ITEM, orderItem)

        val pendingIntent = PendingIntent.getBroadcast(
            context,
            orderItem.orderId.toInt(),
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val triggerTime = SystemClock.elapsedRealtime() + deliveryTime
        alarmManager.set(
            AlarmManager.ELAPSED_REALTIME, // 절전모드에서는 작동 안함
            triggerTime,
            pendingIntent
        )
    }
}