package com.seom.banchan.data.source.local

import com.seom.banchan.data.db.dao.RecentlyDao
import com.seom.banchan.data.db.entity.toMenuModel
import com.seom.banchan.data.db.entity.toRecentlyEntity
import com.seom.banchan.data.source.RecentlyDataSource
import com.seom.banchan.domain.model.home.MenuModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RecentlyDataSourceImpl @Inject constructor(
    private val recentlyDao: RecentlyDao
) : RecentlyDataSource {
    override suspend fun getRecentlys() : Flow<List<MenuModel>> {
        return recentlyDao.getRecentlys().map {
            it.map {
                it.toMenuModel()
            }
        }
    }

    override suspend fun upsertRecently(menuModel: MenuModel,recentlyTime : Long) {
        recentlyDao.upsertRecently(menuModel.toRecentlyEntity(recentlyTime))
    }
}