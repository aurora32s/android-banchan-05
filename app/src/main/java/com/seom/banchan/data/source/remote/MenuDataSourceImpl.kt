package com.seom.banchan.data.source.remote

import com.seom.banchan.data.api.response.BestMenuResponse
import com.seom.banchan.data.source.MenuDataSource

class MenuDataSourceImpl : MenuDataSource {
    override suspend fun getBestMenus(): Result<BestMenuResponse> {
        return Result.success(BestMenuResponse(emptyList()))
    }
}