package com.seom.banchan.worker.system

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class BootCompleteReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        intent?.let {
            if (intent.action == "android.intent.action.BOOT_COMPLETE") {
                // TODO 배달이 완료되지 않은 주문에 대해 알림 재생성
                // Alarm이 지워지는가?? 확인해보기
            }
        } ?: return
    }
}