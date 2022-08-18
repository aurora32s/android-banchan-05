package com.seom.banchan.data.source.local

import com.seom.banchan.data.db.dao.RecentDao
import com.seom.banchan.data.db.entity.toMenuModel
import com.seom.banchan.data.db.entity.toRecentEntity
import com.seom.banchan.data.source.RecentDataSource
import com.seom.banchan.domain.model.home.MenuModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RecentDataSourceImpl @Inject constructor(
    private val recentlyDao: RecentDao
) : RecentDataSource {
    override suspend fun getRecents() : Flow<List<MenuModel>> {
        return recentlyDao.getRecents().map {
            it.map {
                it.toMenuModel()
            }
        }
    }

    override suspend fun upsertRecent(menuModel: MenuModel) {
        recentlyDao.upsertRecent(menuModel.toRecentEntity())
    }
}