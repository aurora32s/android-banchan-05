package com.seom.banchan.ui.model.order

import androidx.annotation.StringRes
import com.seom.banchan.R

/**
 * 주문의 배송 상태
 */
enum class OrderDeliveryState(
    val type: Int, // 0: 배송중, 1: 배송완료
    @StringRes
    val stateTitle: Int, // 배송중, 배송 완료
    @StringRes
    val stateLongTitle: Int
) {
    DELIVERING(0, R.string.order_list_delivering, R.string.order_delivering), // 배송중
    DELIVERED(1, R.string.order_list_delivered, R.string.order_delivered); // 배송 완료

    companion object {
        fun getDeliveryType(deliveryType: Int) =
            values().find { it.type == deliveryType } ?: DELIVERING
    }
}