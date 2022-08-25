package com.seom.banchan

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import com.seom.banchan.data.db.BanChanDatabase
import com.seom.banchan.data.db.dao.OrderDao
import com.seom.banchan.data.db.dao.RecentDao
import com.seom.banchan.data.db.entity.OrderEntity
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
class OrderDaoTest {

    private lateinit var database : BanChanDatabase
    private lateinit var orderDao: OrderDao

    private val orderEntity = OrderEntity(
        orderId = 1,
        createdAt = System.currentTimeMillis(),
        expectedTime = 20 * 1000
    )


    @Before
    fun setup(){
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            BanChanDatabase::class.java
        ).allowMainThreadQueries()
            .build()

        orderDao = database.orderDao()
    }

    @After
    fun cleanUp(){
        database.close()
    }

    @Test
    fun `주문_내역을_추가할_수_있다`() = runTest {

    }

    @Test
    fun `주문_내역들을_추가할_수_있다`() = runTest {

    }

    @Test
    fun `모든_주문_내역들을_조회할_수_있다`() = runTest {

    }

    @Test
    fun `주문ID로_배송_정보를_조회할_수_있다`() = runTest {

    }

    @Test
    fun `주문ID로_주문_정보를_조회할_수_있다`() = runTest {

    }

    @Test
    fun `주문ID로_메뉴_정보를_조회할_수_있다`() = runTest {

    }

    @Test
    fun `주문ID로_해당_주문의_배송_상태를_변경할_수_있다`() = runTest {

    }
}