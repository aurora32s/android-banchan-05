package com.seom.banchan.worker.model

import java.io.Serializable

data class DeliveryAlarmModel(
    val orderId: Long, // 주문 id
    val menuName: String, // 메뉴 이름
    val totalCount: Int, // 메뉴 총 개수
    val expectedTime: Long // 배달 예상 시간
): Serializable
