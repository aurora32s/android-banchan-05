package com.seom.banchan.data.source

import com.seom.banchan.data.db.entity.OrderListEntity
import kotlinx.coroutines.flow.Flow

/**
 * 주문 내역 관리 data source
 */
interface OrderDataSource {
    /**
     * 주문 내역 추가
     */
    suspend fun insertOrder(): Result<Long>

    /**
     * 모든 주문 내역 요창
     * TODO 페이징 기능 추가
     */
    fun getOrderList(): Flow<List<OrderListEntity>>
}