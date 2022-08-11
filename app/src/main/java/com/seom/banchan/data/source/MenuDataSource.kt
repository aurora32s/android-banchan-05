package com.seom.banchan.data.source

import com.seom.banchan.data.api.response.BestMenuResponse
import com.seom.banchan.domain.model.CategoryModel

/**
 * 메뉴에만 관련된 요청 관리
 */
interface MenuDataSource {
    suspend fun getBestMenus(): Result<List<CategoryModel>>
}