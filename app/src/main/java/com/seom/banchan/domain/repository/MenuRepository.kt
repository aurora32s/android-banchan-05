package com.seom.banchan.domain.repository

import com.seom.banchan.data.api.response.BestMenuResponse

interface MenuRepository {
    /**
     * 기획전 메뉴 정보 요청
     */
    fun getBestMenus(): Result<BestMenuResponse>
}