package com.seom.banchan.data.repository

import com.seom.banchan.data.source.RecentlyDataSource
import com.seom.banchan.domain.model.MenuModel
import com.seom.banchan.domain.repository.RecentlyRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RecentlyRepositoryImpl @Inject constructor(
    private val recentlyDataSource: RecentlyDataSource
) : RecentlyRepository {
    override suspend fun getRecentlys(): Flow<List<MenuModel>> {
       return recentlyDataSource.getRecentlys()
    }

    override suspend fun upsertRecently(menuModel: MenuModel, recentlyTime : Long) {
        recentlyDataSource.upsertRecently(menuModel,recentlyTime)
    }
}