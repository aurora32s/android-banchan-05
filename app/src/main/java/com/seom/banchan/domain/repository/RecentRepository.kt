package com.seom.banchan.domain.repository

import com.seom.banchan.domain.model.home.MenuModel
import kotlinx.coroutines.flow.Flow

interface RecentRepository {
    suspend fun getRecents() : Flow<List<MenuModel>>

    suspend fun upsertRecent(menuModel: MenuModel)
}