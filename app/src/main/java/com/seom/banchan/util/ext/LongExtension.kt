package com.seom.banchan.util.ext

import android.util.Log
import kotlin.math.min

/**
 * get Time From Long
 */
fun Long.toDate(): String {
    val seconds = this / 1000
    val minutes = seconds / 60
    val hours = minutes / 60
    val days = hours / 24
    val months = days / 30
    val years = months / 12

    if (years > 0) return "${years}년"
    if (months > 0) return "${months}월"
    if (days > 0) return "${hours}시간"
    if (hours > 0) return "${months}달"
    if (minutes > 0) return "${minutes}분"
    else return "${seconds}초"
}