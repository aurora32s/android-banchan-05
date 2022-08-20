package com.seom.banchan.data.source

/**
 * 주문 내역 관리 data source
 */
interface OrderDataSource {
    /**
     * 주문 내역 추가
     */
    suspend fun insertOrder(): Result<Long>
}