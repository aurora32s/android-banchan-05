package com.seom.banchan.data.repository

import com.seom.banchan.data.api.response.BestMenuResponse
import com.seom.banchan.domain.repository.MenuRepository

class MenuRepositoryImpl : MenuRepository {
    override fun getBestMenus(): Result<BestMenuResponse> {
        return Result.success(BestMenuResponse(emptyList()))
    }
}