package com.seom.banchan.data.recent

import androidx.paging.*
import com.seom.banchan.data.db.dao.RecentDao
import com.seom.banchan.data.db.entity.RecentEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeRecentDao : RecentDao {
    private val recentData : HashMap<String, RecentEntity> = hashMapOf()

    override fun getRecents(): Flow<List<RecentEntity>> {
        val recents = recentData.values.toList().sortedByDescending {
            it.recentTime
        }
        return flow {
            emit(recents)
        }
    }

    override fun getLatestRecents(): Flow<List<RecentEntity>> {
        val recents = recentData.values.toList().sortedByDescending {
            it.recentTime
        }.subList(0,7)
        return flow {
            emit(recents)
        }
    }

    override fun getRecentPaging(): PagingSource<Int, RecentEntity> {
        return RecentPagingSource(recentData)
    }

    override suspend fun insertRecent(recentEntity: RecentEntity) {
        recentData.set(recentEntity.id,recentEntity)
    }

    override suspend fun updateRecent(recentEntity: RecentEntity) {
        recentData.set(recentEntity.id,recentEntity)
    }

    class RecentPagingSource(val recentData : HashMap<String,RecentEntity>): PagingSource<Int,RecentEntity>(){
        override fun getRefreshKey(state: PagingState<Int, RecentEntity>): Int? {
            return state.anchorPosition?.let {
                state.closestPageToPosition(it)?.prevKey?.plus(1)
                    ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
            }
        }

        override suspend fun load(params: LoadParams<Int>): LoadResult<Int, RecentEntity> {
           return try {
               val next = params.key ?: 0
               val response = recentData.values.toList().sortedByDescending {
                   it.recentTime
               }.subList(0,params.loadSize)

               LoadResult.Page(
                   data = response,
                   prevKey = if(next == 0) null else next - 1,
                   nextKey = next + 1
               )
           } catch (e : Exception){
               LoadResult.Error(e)
           }
        }
    }
}

