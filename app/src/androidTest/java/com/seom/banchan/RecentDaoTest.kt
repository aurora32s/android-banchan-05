package com.seom.banchan

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.seom.banchan.data.db.BanChanDatabase
import com.seom.banchan.data.db.dao.RecentDao
import com.seom.banchan.data.db.entity.RecentEntity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class RecentDaoTest {

    private lateinit var database : BanChanDatabase
    private lateinit var recentDao : RecentDao
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
            recentTime = System.currentTimeMillis()-14000L
        )
    )

    @Before
    fun setup(){
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            BanChanDatabase::class.java
        ).allowMainThreadQueries()
            .build()

        recentDao = database.recentDao()
    }

    @After
    fun cleanUp(){
        database.close()
    }

    @Test
    fun `모든_최근_본_상품들을_조회할_수_있다`(){

    }

    @Test
    fun `최대_7개의_최근_본_상품들을_조회할_수_있다`(){

    }

    @Test
    fun `최근_본_상품을_추가하거나_만약에_이미_존재한다면_변경할_수_있다`(){

    }
}