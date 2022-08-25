package com.seom.banchan.data

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

    private lateinit var database: BanChanDatabase
    private lateinit var cartDao: CartDao
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
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            BanChanDatabase::class.java
        ).allowMainThreadQueries()
            .build()

        cartDao = database.cartDao()
    }

    @After
    fun cleanUp() {
        database.close()
    }

    @Test
    fun `카트에_추가가_되었다`() = runTest {
        cartDao.insertOrReplaceCartMenu(cartMenuEntity)
        val menus = cartDao.getCartMenus().first()

        assertThat(
            menus.contains(cartMenuEntity)
        ).isTrue()
    }

    @Test
    fun `이미_추가된_상품을_다시_추가하면_해당_상품은_업데이트된다`() = runTest {
        cartDao.insertOrReplaceCartMenu(
            cartMenuEntity.copy(
                name = "오리 주물럭_조리"
            )
        )
        val menu = cartDao.getCartMenuById(cartMenuEntity.menuId)

        assertThat(
            menu?.let {
                menu.name == "오리 주물럭_조리"
            }
        ).isEqualTo(true)
    }

    @Test
    fun `메뉴ID를_통해_카트에_담긴_상품을_조회할_수_있다`() = runTest {
        cartDao.insertOrReplaceCartMenu(
            cartMenuEntity.copy(
                name = "오리 주물럭_반조리"
            )
        )
        val cartMenu = cartDao.getCartMenuById("HBDEF")
        assertThat(
            this@CartDaoTest.cartMenuEntity.name == cartMenu?.name
        ).isTrue()
    }

    @Test
    fun `카트에_담긴_상품을_모두_조회할_수_있다`() = runTest {
        cartMenuEntityList.forEach {
            cartDao.insertOrReplaceCartMenu(it)
        }

        val cartMenus = cartDao.getCartMenus()
        assertThat(
            cartMenus.first() == cartMenuEntityList
        ).isTrue()
    }

    @Test
    fun `카트에서_메뉴ID를_통해_상품을_삭제할_수_있다`() = runTest {
        cartDao.insertOrReplaceCartMenu(cartMenuEntity)
        cartDao.deleteCartMenu(cartMenuEntity.menuId)

        val cartMenus = cartDao.getCartMenus()
        assertThat(
            cartMenus.first().contains(cartMenuEntity)
        ).isFalse()
    }

    @Test
    fun `선택_상태인_상품읆_모두_삭제할_수_있다`() = runTest { // 선택한 메뉴가 삭제됐는가
        val cartMenus = cartMenuEntityList.map {
            it.copy(selected = true)
        }
        cartDao.deleteCartMenuList(
            cartMenus.filter {
                it.selected == true
            }.map {
                it.menuId
            }
        )

        assertThat(
            cartDao.getCartMenus().first().filter {
                cartMenus.map {
                    it.menuId
                }.contains(it.menuId)
            }.size == 0
        ).isTrue()
    }

    @Test
    fun `메뉴ID를_통해_상품을_선택_상태로_변경할_수_있다`() = runTest { // 해당 메뉴가 선택 상태로 됐는가
        cartDao.insertOrReplaceCartMenu(
            cartMenuEntity.copy(
                selected = false
            )
        )
        cartDao.updateCartMenuSelected(cartMenuEntity.menuId)

        val cartMenu = cartDao.getCartMenuById(cartMenuEntity.menuId)
        assertThat(
            cartMenu?.selected == true
        ).isTrue()
    }

    @Test
    fun `상품들을_모두_전체_선택_상태로_변경할_수_있다`() = runTest { // 전체 선택이 제대로 됐는가
        val cartMenus = cartMenuEntityList.map {
            it.copy(selected = false)
        }
        cartMenus.forEach {
            cartDao.insertOrReplaceCartMenu(it)
        }
        cartDao.updateCartMenuListSelected(
            cartMenus.filter {
                it.selected == false
            }.map {
                it.menuId
            }
        )
        assertThat(
            cartDao.getCartMenus().first().filter {
                it.selected == false
            }.size == 0
        ).isTrue()

    }

    @Test
    fun `선택된_상품들을_모두_선택_해제_상태로_변경할_수_있다`() = runTest {
        val cartMenus = cartMenuEntityList.map {
            it.copy(selected = true)
        }
        cartMenus.forEach {
            cartDao.insertOrReplaceCartMenu(it)
        }
        cartDao.updateCartMenuListSelected(
            cartMenus.filter {
                it.selected == true
            }.map {
                it.menuId
            }
        )
        assertThat(
            cartDao.getCartMenus().first().filter {
                it.selected == true
            }.size == 0
        ).isTrue()
    }

    @Test
    fun `상품의_개수를_변경할_수_있다`() = runTest { // 메뉴 개수 변경이 제대로 됐는가
        cartDao.insertOrReplaceCartMenu(
            cartMenuEntity.copy(
                count = 1
            )
        )
        cartDao.updateCartMenuCount(cartMenuEntity.menuId, 20)

        val cartMenu = cartDao.getCartMenuById(cartMenuEntity.menuId)
        assertThat(
            cartMenu?.count == 20
        ).isTrue()
    }

    @Test
    fun `싱픔의_개수를_1_증가시킬_수_있다`() = runTest { // 메뉴 개수 증가가 제대로 됐는가
        cartDao.insertOrReplaceCartMenu(
            cartMenuEntity.copy(
                count = 1
            )
        )
        cartDao.updateCartMenuCountIncrease(cartMenuEntity.menuId)

        val cartMenu = cartDao.getCartMenuById(cartMenuEntity.menuId)
        assertThat(
            cartMenu?.count == 2
        ).isTrue()
    }

    @Test
    fun `상품의_개수를_1_감소시킬_수_있다`() = runTest { // 메뉴 개수 감소가 제대로 됐는가
        cartDao.insertOrReplaceCartMenu(
            cartMenuEntity.copy(
                count = 30
            )
        )
        cartDao.updateCartMenuCountDecrease(cartMenuEntity.menuId)

        val cartMenu = cartDao.getCartMenuById(cartMenuEntity.menuId)
        assertThat(
            cartMenu?.count == 29
        ).isTrue()
    }
}
