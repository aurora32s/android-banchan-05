package com.seom.banchan

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import com.seom.banchan.data.db.BanChanDatabase
import com.seom.banchan.data.db.dao.CartDao
import com.seom.banchan.data.db.entity.CartMenuEntity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class CartDaoTest {

    private lateinit var database : BanChanDatabase
    private lateinit var cartDao : CartDao
    private val cartMenuEntity = CartMenuEntity(
        menuId = "HBDEF",
        name = "오리 주물럭_반조리",
        image = "http://public.codesquad.kr/jk/storeapp/data/main/1155_ZIP_P_0081_T.jpg",
        salePrice = 12640,
        count = 1,
        selected = true
    )

    @Before
    fun setup(){
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            BanChanDatabase::class.java
        ).allowMainThreadQueries()
            .build()

        cartDao = database.cartDao()
    }


    @Test
    fun isIn_CartList_true() = runTest {
        cartDao.insertOrReplaceCartMenu(cartMenuEntity)
        val menus = cartDao.getCartMenus().first()

        assertThat(
            menus.contains(cartMenuEntity)
        )
    }
}