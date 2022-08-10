package com.seom.banchan.data.source

import com.seom.banchan.data.api.response.BestMenuResponse

/**
 * 메뉴에만 관련된 요청 관리
 */
interface MenuDataSource {
    suspend fun getBestMenus(): Result<BestMenuResponse>
}