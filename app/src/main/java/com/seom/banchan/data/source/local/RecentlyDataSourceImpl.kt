package com.seom.banchan.data.source.local

import com.seom.banchan.data.db.BanchanDatabase
import com.seom.banchan.data.db.dao.RecentlyDao
import com.seom.banchan.data.db.entity.RecentlyEntity
import com.seom.banchan.data.db.entity.toMenuModelList
import com.seom.banchan.data.db.entity.toRecentlyEntity
import com.seom.banchan.data.source.RecentlyDataSource
import com.seom.banchan.domain.model.MenuModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RecentlyDataSourceImpl @Inject constructor(
    private val recentlyDao: RecentlyDao
) : RecentlyDataSource {
    override suspend fun getRecentlys() : Flow<List<MenuModel>> {
        return recentlyDao.getRecentlys().map {
            it.toMenuModelList()
        }
    }

    override suspend fun upsertRecently(menuModel: MenuModel,recentlyTime : Long) {
        recentlyDao.upsertRecently(menuModel.toRecentlyEntity(recentlyTime))
    }
}