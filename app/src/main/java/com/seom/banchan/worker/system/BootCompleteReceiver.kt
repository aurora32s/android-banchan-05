package com.seom.banchan.worker.system

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.seom.banchan.worker.work.ResetAlarmWorker

class BootCompleteReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {
        intent?.let {
            if (intent.action == "android.intent.action.BOOT_COMPLETED") {
                OneTimeWorkRequestBuilder<ResetAlarmWorker>()
                    .addTag(ResetAlarmWorker.TAG)
                    .build()
                    .run {
                        WorkManager.getInstance(context)
                            .beginWith(this)
                            .enqueue()
                    }
            }
        } ?: return
    }
}