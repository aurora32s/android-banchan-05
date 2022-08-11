package com.seom.banchan.domain.repository

import com.seom.banchan.data.api.response.BestMenuResponse
import com.seom.banchan.domain.model.CategoryModel

interface MenuRepository {
    /**
     * 기획전 메뉴 정보 요청
     */
    suspend fun getBestMenus(): Result<List<CategoryModel>>
}