package com.seom.banchan.domain.repository

import com.seom.banchan.domain.model.MenuModel
import kotlinx.coroutines.flow.Flow

interface RecentlyRepository {
    suspend fun getRecentlys() : Flow<List<MenuModel>>

    suspend fun upsertRecently(menuModel: MenuModel,recentlyTime : Long)
}