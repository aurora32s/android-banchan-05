package com.seom.banchan.ui.model.order

import androidx.annotation.StringRes
import com.seom.banchan.R

/**
 * 주문의 배송 상태
 */
enum class OrderDeliveryState(
    @StringRes
    val stateTitle: Int
) {
    PREPARE(R.string.order_list_prepare), // 배송 준비중
    DELIVERING(R.string.order_list_delivering), // 배송중
    DELIVERED(R.string.order_list_delivered) // 배송 완료
}