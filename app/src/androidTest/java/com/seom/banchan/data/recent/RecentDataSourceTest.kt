package com.seom.banchan.data.recent

import androidx.paging.PagingData
import com.seom.banchan.data.db.dao.RecentDao
import com.seom.banchan.data.db.entity.RecentEntity
import com.seom.banchan.data.db.entity.toMenuModel
import com.seom.banchan.data.source.RecentDataSource
import com.seom.banchan.data.source.local.RecentDataSourceImpl
import com.seom.banchan.domain.model.home.MenuModel
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class RecentDataSourceTest {

    private lateinit var recentDao: RecentDao
    private lateinit var recentDataSource : RecentDataSource

    private val recentEntity = RecentEntity(
        id = "H1939",
        image = "http://public.codesquad.kr/jk/storeapp/data/side/17_ZIP_P_6014_T.jpg",
        title = "호두 멸치볶음",
        salePrice = 5220,
        description = "",
        normalPrice = 6000
    )
    @Before
    fun setup(){
        recentDao = FakeRecentDao()
        recentDataSource = RecentDataSourceImpl(
            recentDao
        )
    }

    @Test
    fun `최근_본_상품_목록을_조회할_수_있다`() = runTest {
        recentDataSource.upsertRecent(recentEntity.toMenuModel())

        val expected = listOf(recentEntity.toMenuModel())
        val actual = recentDataSource.getRecents().first()

        assertEquals(
            expected,actual
        )
    }

    @Test
    fun `최근_본_상품_목록_페이징`() = runTest {
        recentDataSource.upsertRecent(recentEntity.toMenuModel())
        //TODO
    }

    @Test
    fun `최근_본_상품을_최대_7개_조회할_수_있다`() = runTest {
        val recentList = listOf(
            RecentEntity(
                id = "1", image = "", title = "", description = "", normalPrice = 0, salePrice = 0,
                recentTime = System.currentTimeMillis()
            ).toMenuModel(),
            RecentEntity(
                id = "5", image = "", title = "", description = "", normalPrice = 0, salePrice = 0,
                recentTime = System.currentTimeMillis() - 500
            ).toMenuModel(),
            RecentEntity(
                id = "3", image = "", title = "", description = "", normalPrice = 0, salePrice = 0,
                recentTime = System.currentTimeMillis() - 300
            ).toMenuModel(),
            RecentEntity(
                id = "6", image = "", title = "", description = "", normalPrice = 0, salePrice = 0,
                recentTime = System.currentTimeMillis() - 600
            ).toMenuModel(),
            RecentEntity(
                id = "4", image = "", title = "", description = "", normalPrice = 0, salePrice = 0,
                recentTime = System.currentTimeMillis() - 400
            ).toMenuModel(),
            RecentEntity(
                id = "2", image = "", title = "", description = "", normalPrice = 0, salePrice = 0,
                recentTime = System.currentTimeMillis() - 200
            ).toMenuModel(),
            RecentEntity(
                id = "7", image = "", title = "", description = "", normalPrice = 0, salePrice = 0,
                recentTime = System.currentTimeMillis() - 700
            ).toMenuModel(),
            RecentEntity(
                id = "8", image = "", title = "", description = "", normalPrice = 0, salePrice = 0,
                recentTime = System.currentTimeMillis() - 800
            ).toMenuModel()
        )

        recentList.forEach {
            recentDataSource.upsertRecent(it)
        }

        val expected = recentList.sortedByDescending {
            it.recentTime
        }.subList(0,7).size

        val actual = recentDataSource.getLatestRecents().first().size

        assertEquals(
            expected,actual
        )
    }
}