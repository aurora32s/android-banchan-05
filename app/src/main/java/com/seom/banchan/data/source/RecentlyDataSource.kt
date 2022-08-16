package com.seom.banchan.data.source

import com.seom.banchan.data.db.entity.RecentlyEntity
import com.seom.banchan.domain.model.MenuModel
import kotlinx.coroutines.flow.Flow

interface RecentlyDataSource {
    suspend fun getRecentlys() : Flow<List<MenuModel>>

    suspend fun upsertRecently(menuModel: MenuModel,recentlyTime : Long)
}