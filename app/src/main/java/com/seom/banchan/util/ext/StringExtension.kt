package com.seom.banchan.util.ext

/**
 * 00,000원으로 들어오는 string 을 int price 로 변경
 */
fun String?.toIntPrice(): Int {
    if (isNullOrBlank()) return 0
    return replace("[,원]".toRegex(), "").toInt()
}