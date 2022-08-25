package com.seom.banchan.worker.notification

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.core.app.NotificationCompat
import com.seom.banchan.R
import com.seom.banchan.ui.main.MainActivity
import com.seom.banchan.worker.model.DeliveryAlarmModel

object DeliveryNotificationManager {
    fun create(context: Context, deliveryAlarmModel: DeliveryAlarmModel): Notification {
        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        createNotificationChannel(notificationManager)
        return buildNotification(
            context,
            notificationManager,
            deliveryAlarmModel
        )
    }

    private fun createNotificationChannel(notificationManager: NotificationManager) {
        // Oreo
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_HIGH
            )
            channel.enableLights(true)
            channel.lightColor = Color.RED
            channel.enableVibration(true)
            channel.description = CHANNEL_DESC
            notificationManager.createNotificationChannel(channel)
        }
    }

    @SuppressLint("ObsoleteSdkInt")
    fun buildNotification(
        context: Context,
        notificationManager: NotificationManager,
        deliveryAlarmModel: DeliveryAlarmModel
    ): Notification {
        val contentIntent = Intent(context, MainActivity::class.java)
        contentIntent.putExtra(MainActivity.KEY_ORDER_ID, deliveryAlarmModel.orderId)

        val contentPendingIntent = PendingIntent.getActivity(
            context,
            deliveryAlarmModel.orderId.toInt(),
            contentIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        return NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_app_icon_small)
            .setContentTitle("배달이 도착했어요!")
            .setContentText(
                "" +
                        "${deliveryAlarmModel.menuName} " +
                        if (deliveryAlarmModel.totalCount > 1)
                            "외 ${deliveryAlarmModel.totalCount - 1}개"
                        else ""
            )
            .setContentIntent(contentPendingIntent)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)
            .setDefaults(NotificationCompat.DEFAULT_ALL)
            .apply {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    this.color = context.resources.getColor(R.color.primaryAccent, null)
                }
            }.build()
    }

    private const val CHANNEL_ID = "notification_channel"
    private const val CHANNEL_NAME = "배달 알림 채널"
    private const val CHANNEL_DESC = "주문하신 배달이 완료되었을 떄 알려주는 채널입니다."
}