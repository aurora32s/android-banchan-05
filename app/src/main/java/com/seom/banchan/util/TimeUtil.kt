package com.seom.banchan.util

import android.annotation.SuppressLint
import java.lang.Math.abs
import java.text.SimpleDateFormat
import java.util.*

object TimeUtil {
    private const val SECOND = 1000L
    private const val MINUTE = SECOND * 60
    private const val HOUR = MINUTE * 60
    private const val DAY = HOUR * 24
    private const val MONTH = DAY * 30
    private const val YEAR = MONTH * 12

    @SuppressLint("SimpleDateFormat")
    fun getTimeData(updated: Long): String {
        try {
            val currentDate = Date().time
            val diff = currentDate - updated

            return when (diff) {
                in 0 until SECOND -> {
                    "방금 전"
                }
                in SECOND until MINUTE -> {
                    "1분 전"
                }
                in MINUTE until HOUR -> {
                    "${(diff / MINUTE).toInt()}분 전"
                }
                in HOUR until DAY -> {
                    "${(diff / HOUR).toInt()}시간 전"
                }
                in DAY until MONTH -> {
                    "${(diff / DAY).toInt()}일 전"
                }
                in MONTH until YEAR -> {
                    "${(diff / MONTH).toInt()}달 전"
                }
                else -> {
                    "${(diff / YEAR).toInt()}년 전"
                }
            }
        } catch (e: Exception) {
            return SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm").format(updated) // 에러 발생하면 시간으로 출력
        }
    }

}