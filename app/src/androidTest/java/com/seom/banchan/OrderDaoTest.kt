package com.seom.banchan

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.seom.banchan.data.db.BanChanDatabase
import com.seom.banchan.data.db.dao.OrderDao
import com.seom.banchan.data.db.dao.RecentDao
import com.seom.banchan.data.db.entity.RecentEntity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class OrderDaoTest {

    private lateinit var database : BanChanDatabase
    private lateinit var orderDao: OrderDao


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
    fun `주문_내역을_추가할_수_있다`(){

    }


}