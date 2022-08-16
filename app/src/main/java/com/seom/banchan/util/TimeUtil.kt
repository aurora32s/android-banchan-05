package com.seom.banchan.util

import java.lang.Math.abs
import java.text.SimpleDateFormat
import java.util.*

object TimeUtil {
    private const val SECOND = 1000
    private const val MINUTE = SECOND * 60
    private const val HOUR = MINUTE * 60
    private const val DAY = HOUR * 24

    fun getTimeData(updated: Long): String {
        val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.KOREAN)
        try {

            val currentDate = Calendar.getInstance().apply {
                timeInMillis = Date().time
            }

            val targetDate = Calendar.getInstance().apply {
                timeInMillis = parser.parse(parser.format(updated))?.time ?: 0L
            }

            return when (val difference = abs(currentDate.timeInMillis - targetDate.timeInMillis)) {
                in 0 until SECOND -> {
                    "방금 전"
                }
                in SECOND until MINUTE -> {
                    "1분 전"
                }
                in MINUTE until HOUR -> {
                    "${(difference / MINUTE).toInt()}분 전"
                }
                in HOUR until DAY -> {
                    "${(difference / HOUR).toInt()}시간 전"
                }
                else -> {
                    "${(difference / DAY).toInt()}일 전"
                }
            }
        } catch (e: Exception) {
            return parser.format(updated)
        }
    }

}