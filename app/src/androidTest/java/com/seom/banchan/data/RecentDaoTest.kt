package com.seom.banchan.data

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import com.seom.banchan.data.db.BanChanDatabase
import com.seom.banchan.data.db.dao.RecentDao
import com.seom.banchan.data.db.entity.RecentEntity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class RecentDaoTest {

    private lateinit var database: BanChanDatabase
    private lateinit var recentDao: RecentDao
    private val recentEntity = RecentEntity(
        id = "HBDEF",
        title = "오리 주물럭_반조리",
        image = "http://public.codesquad.kr/jk/storeapp/data/main/1155_ZIP_P_0081_T.jpg",
        salePrice = 12640,
        normalPrice = 15800,
        description = "감칠맛 나는 매콤한 양념",
        recentTime = System.currentTimeMillis()
    )

    private val recentMenuEntityList = listOf(
        RecentEntity(
            id = "HBDEF",
            title = "오리 주물럭_반조리",
            image = "http://public.codesquad.kr/jk/storeapp/data/main/1155_ZIP_P_0081_T.jpg",
            salePrice = 12640,
            normalPrice = 15800,
            description = "감칠맛 나는 매콤한 양념",
            recentTime = System.currentTimeMillis()
        ),
        RecentEntity(
            id = "HDF73",
            title = "잡채",
            image = "http://public.codesquad.kr/jk/storeapp/data/main/310_ZIP_P_0012_T.jpg",
            salePrice = 11610,
            normalPrice = 12900,
            description = "탱글한 면발과 맛깔진 고명이 가득",
            recentTime = System.currentTimeMillis() - 14000L
        )
    )

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            BanChanDatabase::class.java
        ).allowMainThreadQueries()
            .build()

        recentDao = database.recentDao()
    }

    @After
    fun cleanUp() {
        database.close()
    }

    @Test
    fun `최근_본_상품을_추가할_수_있다`() = runTest {

        recentDao.upsertRecent(recentEntity)

        val recentMenus = recentDao.getRecents()
        assertThat(
            recentMenus.first().contains(recentEntity)
        ).isTrue()
    }

    @Test
    fun `최근_본_상품이_만약에_이미_존재한다면_변경할_수_있다`() = runTest {
        val recentMenu = recentEntity
        recentDao.upsertRecent(
            recentMenu.copy(
                title = "오리 주물럭_반조리"
            )
        )

        val recentMenus = recentDao.getRecents()
        assertThat(
            recentMenus.first().filter {
                it.id == recentMenu.id
            }.first().title == "오리 주물럭 반조리"
        ).isTrue()
    }

    @Test
    fun `모든_최근_본_상품들을_조회할_수_있다`() = runTest {
        recentMenuEntityList.forEach {
            recentDao.upsertRecent(it)
        }

        val recentMenus = recentDao.getRecents()

        assertThat(
            recentMenus.first() == recentMenuEntityList
        ).isTrue()
    }

    @Test
    fun `최대_7개의_최근_본_상품들을_조회할_수_있다`() = runTest {
        recentDao.upsertRecent(
            RecentEntity(
                id = "1", image = "", title = "", description = "", normalPrice = 0, salePrice = 0,
                recentTime = System.currentTimeMillis()
            )
        )
        recentDao.upsertRecent(
            RecentEntity(
                id = "2", image = "", title = "", description = "", normalPrice = 0, salePrice = 0,
                recentTime = System.currentTimeMillis() - 100
            )
        )
        recentDao.upsertRecent(
            RecentEntity(
                id = "3", image = "", title = "", description = "", normalPrice = 0, salePrice = 0,
                recentTime = System.currentTimeMillis() - 110
            )
        )
        recentDao.upsertRecent(
            RecentEntity(
                id = "4", image = "", title = "", description = "", normalPrice = 0, salePrice = 0,
                recentTime = System.currentTimeMillis() - 120
            )
        )
        recentDao.upsertRecent(
            RecentEntity(
                id = "5", image = "", title = "", description = "", normalPrice = 0, salePrice = 0,
                recentTime = System.currentTimeMillis() - 130
            )
        )
        recentDao.upsertRecent(
            RecentEntity(
                id = "6", image = "", title = "", description = "", normalPrice = 0, salePrice = 0,
                recentTime = System.currentTimeMillis() - 140
            )
        )
        recentDao.upsertRecent(
            RecentEntity(
                id = "7", image = "", title = "", description = "", normalPrice = 0, salePrice = 0,
                recentTime = System.currentTimeMillis() - 150
            )
        )
        recentDao.upsertRecent(
            RecentEntity(
                id = "8", image = "", title = "", description = "", normalPrice = 0, salePrice = 0,
                recentTime = System.currentTimeMillis() - 160
            )
        )

        val latestRecentMenus = recentDao.getLatestRecents()
        assertThat(
            latestRecentMenus.first().size == 7
        ).isTrue()
    }

    @Test
    fun `최대_7개의_최근_본_상품들을_최근_순서대로_조회할_수_있다`() = runTest {
        val recentList = listOf(
            RecentEntity(
                id = "1", image = "", title = "", description = "", normalPrice = 0, salePrice = 0,
                recentTime = System.currentTimeMillis()
            ),
            RecentEntity(
                id = "5", image = "", title = "", description = "", normalPrice = 0, salePrice = 0,
                recentTime = System.currentTimeMillis() - 500
            ),
            RecentEntity(
                id = "3", image = "", title = "", description = "", normalPrice = 0, salePrice = 0,
                recentTime = System.currentTimeMillis() - 300
            ),
            RecentEntity(
                id = "6", image = "", title = "", description = "", normalPrice = 0, salePrice = 0,
                recentTime = System.currentTimeMillis() - 600
            ),
            RecentEntity(
                id = "4", image = "", title = "", description = "", normalPrice = 0, salePrice = 0,
                recentTime = System.currentTimeMillis() - 400
            ),
            RecentEntity(
                id = "2", image = "", title = "", description = "", normalPrice = 0, salePrice = 0,
                recentTime = System.currentTimeMillis() - 200
            ),
            RecentEntity(
                id = "7", image = "", title = "", description = "", normalPrice = 0, salePrice = 0,
                recentTime = System.currentTimeMillis() - 700
            ),
            RecentEntity(
                id = "8", image = "", title = "", description = "", normalPrice = 0, salePrice = 0,
                recentTime = System.currentTimeMillis() - 800
            )
        )

        recentList.forEach {
            recentDao.upsertRecent(it)
        }

        val latestRecentMenus = recentDao.getLatestRecents()
        assertThat(
            latestRecentMenus.first() == recentList.sortedByDescending {
                it.recentTime
            }.subList(0,7)
        ).isTrue()
    }
}