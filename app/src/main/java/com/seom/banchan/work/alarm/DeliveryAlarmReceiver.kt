package com.seom.banchan.work.alarm

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class DeliveryAlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        Log.d(TAG, "on Received")
    }

    companion object {
        const val TAG = ".DeliveryAlarmReceiver"

        const val KEY_ORDER_ITEM = "order_item"
    }
}