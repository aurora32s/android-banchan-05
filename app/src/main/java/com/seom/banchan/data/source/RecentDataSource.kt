package com.seom.banchan.data.source

import com.seom.banchan.domain.model.home.MenuModel
import kotlinx.coroutines.flow.Flow

interface RecentDataSource {
    suspend fun getRecents() : Flow<List<MenuModel>>

    suspend fun upsertRecent(menuModel: MenuModel)
}