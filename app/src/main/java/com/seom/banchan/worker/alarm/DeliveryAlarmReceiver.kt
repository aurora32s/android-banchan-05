package com.seom.banchan.worker.alarm

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.util.Log
import com.seom.banchan.BuildConfig
import com.seom.banchan.worker.model.DeliveryAlarmModel
import com.seom.banchan.worker.notification.DeliveryNotificationManager

class DeliveryAlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {
        val deliveryInfo = intent?.getSerializableExtra(KEY_ORDER_ITEM) ?: return
        (deliveryInfo as? DeliveryAlarmModel)?.let {
            DeliveryNotificationManager.create(context, deliveryInfo)
        }
    }

    companion object {
        const val TAG = ".DeliveryAlarmReceiver"
        const val KEY_ORDER_ITEM = "order_item"
    }
}