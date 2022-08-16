package com.seom.banchan.data.source

import com.seom.banchan.domain.model.home.MenuModel
import kotlinx.coroutines.flow.Flow

interface RecentlyDataSource {
    suspend fun getRecentlys() : Flow<List<MenuModel>>

    suspend fun upsertRecently(menuModel: MenuModel)
}