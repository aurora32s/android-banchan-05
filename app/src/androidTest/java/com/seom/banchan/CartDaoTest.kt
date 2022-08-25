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
import org.junit.After
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

    private val cartMenuEntityList = listOf(
        CartMenuEntity(
            menuId = "HBDEF",
            name = "오리 주물럭_반조리",
            image = "http://public.codesquad.kr/jk/storeapp/data/main/1155_ZIP_P_0081_T.jpg",
            salePrice = 12640,
            count = 1,
            selected = true
        ),
        CartMenuEntity(
            menuId = "HDF73",
            name = "잡채",
            image = "http://public.codesquad.kr/jk/storeapp/data/main/310_ZIP_P_0012_T.jpg",
            salePrice = 11610,
            count = 1,
            selected = true
        )
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

    @After
    fun cleanUp(){
        database.close()
    }

    @Test
    fun insert_CartList_true() = runTest {
        cartDao.insertOrReplaceCartMenu(cartMenuEntity)
        val menus = cartDao.getCartMenus().first()

        assertThat(
            menus.contains(cartMenuEntity)
        ).isTrue()
    }

    @Test
    fun update_CartList_true() = runTest {
        cartDao.insertOrReplaceCartMenu(cartMenuEntity.copy(
            name = "오리 주물럭_조리"
        ))
        val menu = cartDao.getCartMenuById(cartMenuEntity.menuId)

        assertThat(
            menu?.let {
                menu.name == "오리 주물럭_조리"
            }
        ).isEqualTo(true)
    }

    @Test
    fun isSame_CartMenu_true() = runTest {
        cartDao.insertOrReplaceCartMenu(cartMenuEntity.copy(
            name = "오리 주물럭_반조리"
        ))
        val cartMenu = cartDao.getCartMenuById("HBDEF")
        assertThat(
            this@CartDaoTest.cartMenuEntity.name == cartMenu?.name
        ).isTrue()
    }

    @Test
    fun isSame_CartMenus_true() = runTest {
        cartMenuEntityList.forEach {
            cartDao.insertOrReplaceCartMenu(it)
        }

        val cartMenus = cartDao.getCartMenus()
        assertThat(
            cartMenus.first() == cartMenuEntityList
        ).isTrue()
    }

    @Test
    fun isDeleted_CartMenu_true() = runTest {
        cartDao.insertOrReplaceCartMenu(cartMenuEntity)
        cartDao.deleteCartMenu(cartMenuEntity.menuId)

        val cartMenus = cartDao.getCartMenus()
        assertThat(
            cartMenus.first().contains(cartMenuEntity)
        ).isFalse()
    }

    @Test
    fun isDeleted_selectedCartMenus_true() = runTest { // 선택한 메뉴가 삭제됐는가

    }

    @Test
    fun isUpdated_selectedCartMenu_true() = runTest { // 해당 메뉴가 선택 상태로 됐는가
        cartDao.insertOrReplaceCartMenu(cartMenuEntity.copy(
            selected = false
        ))
        cartDao.updateCartMenuSelected(cartMenuEntity.menuId)

        val cartMenu = cartDao.getCartMenuById(cartMenuEntity.menuId)
        assertThat(
            cartMenu?.selected == true
        ).isTrue()
    }

    @Test
    fun isUpdated_selectedCartMenus_true() = runTest { // 전체 선택이 제대로 됐는가

    }

    @Test
    fun isUpdated_countCartMenu_true() = runTest { // 메뉴 개수 변경이 제대로 됐는가
        cartDao.insertOrReplaceCartMenu(cartMenuEntity.copy(
            count = 1
        ))
        cartDao.updateCartMenuCount(cartMenuEntity.menuId, 20)

        val cartMenu = cartDao.getCartMenuById(cartMenuEntity.menuId)
        assertThat(
            cartMenu?.count == 20
        ).isTrue()
    }

    @Test
    fun isUpdated_increaseCountCartMenu_true() = runTest { // 메뉴 개수 증가가 제대로 됐는가
        cartDao.insertOrReplaceCartMenu(cartMenuEntity.copy(
            count = 1
        ))
        cartDao.updateCartMenuCountIncrease(cartMenuEntity.menuId)

        val cartMenu = cartDao.getCartMenuById(cartMenuEntity.menuId)
        assertThat(
            cartMenu?.count == 2
        ).isTrue()
    }

    @Test
    fun isUpdated_decreaseCountCartMenu_true() = runTest { // 메뉴 개수 감소가 제대로 됐는가
        cartDao.insertOrReplaceCartMenu(cartMenuEntity.copy(
            count = 30
        ))
        cartDao.updateCartMenuCountDecrease(cartMenuEntity.menuId)

        val cartMenu = cartDao.getCartMenuById(cartMenuEntity.menuId)
        assertThat(
            cartMenu?.count == 29
        ).isTrue()
    }
}
