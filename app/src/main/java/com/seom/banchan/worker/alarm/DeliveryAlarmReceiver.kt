package com.seom.banchan.worker.alarm

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.util.Log
import androidx.work.Data
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.seom.banchan.BuildConfig
import com.seom.banchan.worker.model.DeliveryAlarmModel
import com.seom.banchan.worker.notification.DeliveryNotificationManager
import com.seom.banchan.worker.work.DeliveryStateChangeWork

class DeliveryAlarmReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent?) {
        val deliveryInfo = intent?.getSerializableExtra(KEY_ORDER_ITEM) ?: return

        (deliveryInfo as? DeliveryAlarmModel)?.let {
            // 1, WorkManager 로 DB 업데이트
            val orderIdData =
                Data.Builder().putLong(DeliveryStateChangeWork.KEY_ORDER_ID, it.orderId).build()
            OneTimeWorkRequestBuilder<DeliveryStateChangeWork>()
                .setInputData(orderIdData)
                .addTag(deliveryInfo.orderId.toString())
                .build()
                .run {
                    WorkManager.getInstance(context).beginWith(this).enqueue()
                }

            // 2. Notification 알림
            DeliveryNotificationManager.create(context, deliveryInfo)
        }
    }

    companion object {
        const val TAG = ".DeliveryAlarmReceiver"
        const val KEY_ORDER_ITEM = "order_item"
    }
}