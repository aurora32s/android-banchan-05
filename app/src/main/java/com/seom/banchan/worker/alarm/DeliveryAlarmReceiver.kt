package com.seom.banchan.worker.alarm

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class DeliveryAlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val deliveryInfo = intent?.getSerializableExtra(KEY_ORDER_ITEM) ?: return
        Log.d(TAG, "on Received $deliveryInfo")
    }

    companion object {
        const val TAG = ".DeliveryAlarmReceiver"

        const val KEY_ORDER_ITEM = "order_item"
    }
}