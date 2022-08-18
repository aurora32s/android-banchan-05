package com.seom.banchan.data.repository

import com.seom.banchan.data.source.RecentDataSource
import com.seom.banchan.domain.model.home.MenuModel
import com.seom.banchan.domain.repository.RecentRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RecentRepositoryImpl @Inject constructor(
    private val recentDataSource: RecentDataSource
) : RecentRepository {
    override suspend fun getRecents(): Flow<List<MenuModel>> {
       return recentDataSource.getRecents()
    }

    override suspend fun upsertRecent(menuModel: MenuModel) {
        recentDataSource.upsertRecent(menuModel)
    }
}