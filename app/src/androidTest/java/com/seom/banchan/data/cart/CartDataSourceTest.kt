package com.seom.banchan.data.cart

import com.google.common.base.Verify.verify
import com.seom.banchan.data.db.dao.CartDao
import com.seom.banchan.data.source.CartDataSource
import com.seom.banchan.data.source.local.CartDataSourceImpl
import com.seom.banchan.domain.model.cart.CartMenuModel
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class CartDataSourceTest {
    private lateinit var cartDao: CartDao
    private lateinit var cartDataSource: CartDataSource

    private val cartMenuModel = CartMenuModel(
        menuId = "H1939",
        image = "http://public.codesquad.kr/jk/storeapp/data/side/17_ZIP_P_6014_T.jpg",
        name = "호두 멸치볶음",
        salePrice = 5220,
        count = 1,
        selected = true
    )
    private val cartMenuModel1 = CartMenuModel(
        menuId = "HBBCC",
        image = "http://public.codesquad.kr/jk/storeapp/data/side/48_ZIP_P_5008_T.jpg",
        name = "새콤달콤 오징어무침",
        salePrice = 6000,
        count = 1,
        selected = true
    )

    @Before
    fun setup() {
        cartDao = FakeCartDao()
        cartDataSource = CartDataSourceImpl(
            cartDao
        )
    }

    @Test
    fun `해당_상품을_추가하거나_이미_존재_시_변경할_수_있다`() = runTest {
        val expected = Result.success(1L)
        val actual = cartDataSource.addOrUpdateMenuToCart(
            cartMenuItem = cartMenuModel
        )
        assertEquals(
            expected, actual
        )
    }

    @Test
    fun `카트에서_모든_상품들을_조회할_수_있다`() = runTest {
        cartDataSource.addOrUpdateMenuToCart(
            cartMenuItem = cartMenuModel
        )
        val expected = cartMenuModel
        val actual = cartDataSource.getCartMenusId().first().first()
        assertEquals(
            expected, actual
        )
    }

    @Test
    fun `카트에서_해당_상품을_삭제할_수_있다`() = runTest {
        cartDataSource.addOrUpdateMenuToCart(
            cartMenuItem = cartMenuModel
        )
        val expected = 1
        val actual = cartDataSource.deleteCartMenu(cartMenuModel.menuId)
        verify(
            !cartDataSource.getCartMenusId().first().contains(cartMenuModel)
        )
        assertEquals(
            expected, actual
        )
    }

    @Test
    fun `카트에서_해당_상품들을_삭제할_수_있다`() = runTest {
        cartDataSource.addOrUpdateMenuToCart(
            cartMenuItem = cartMenuModel
        )
        cartDataSource.addOrUpdateMenuToCart(
            cartMenuItem = cartMenuModel1
        )
        val expected = 1
        val actual =
            cartDataSource.deleteCartMenuList(listOf(cartMenuModel.menuId, cartMenuModel1.menuId))
        verify(
            !(cartDataSource.getCartMenusId().first().contains(cartMenuModel) &&
                    cartDataSource.getCartMenusId().first().contains(cartMenuModel1))
        )
        assertEquals(
            expected, actual
        )
    }

    @Test
    fun `카트에서_해당_상품들을_선택_상태를_변경할_수_있다`() = runTest {
        cartDataSource.addOrUpdateMenuToCart(
            cartMenuItem = cartMenuModel
        )
        cartDataSource.addOrUpdateMenuToCart(
            cartMenuItem = cartMenuModel1
        )
        val expected = 1
        val actual =
            cartDataSource.updateCartMenuListSelected(listOf(cartMenuModel.menuId, cartMenuModel1.menuId))
        verify(
            cartDataSource.getCartMenusId().first().filter {
                it.selected == false
            }.size == 2
        )
        assertEquals(
            expected, actual
        )
    }

    @Test
    fun `카트에서_해당_상품의_선택_상태를_변경할_수_있다`() = runTest {
        cartDataSource.addOrUpdateMenuToCart(
            cartMenuItem = cartMenuModel
        )
        val expected = 1
        val actual =
            cartDataSource.updateCartMenuSelected(cartMenuModel.menuId)
        verify(
            cartDataSource.getCartMenusId().first().first().selected == false
        )
        assertEquals(
            expected, actual
        )
    }

    @Test
    fun `카트에서_해당_상품의_수량을_증가시킬_수_있다`() = runTest {
        cartDataSource.addOrUpdateMenuToCart(
            cartMenuItem = cartMenuModel
        )
        val expected = 1
        val actual =
            cartDataSource.updateCartMenuCountIncrease(cartMenuModel.menuId)
        verify(
            cartDataSource.getCartMenusId().first().first().count == 2
        )
        assertEquals(
            expected, actual
        )
    }

    @Test
    fun `카트에서_해당_상품의_수량을_감소시킬_수_있다`() = runTest {
        cartDataSource.addOrUpdateMenuToCart(
            cartMenuItem = cartMenuModel.copy(
                count = 2
            )
        )
        val expected = 1
        val actual =
            cartDataSource.updateCartMenuCountDecrease(cartMenuModel.menuId)
        verify(
            cartDataSource.getCartMenusId().first().first().count == 1
        )
        assertEquals(
            expected, actual
        )
    }

    @Test
    fun `카트에서_해당_상품의_수량을_변경시킬_수_있다`() = runTest {
        cartDataSource.addOrUpdateMenuToCart(
            cartMenuItem = cartMenuModel
        )
        val expected = 1
        val actual =
            cartDataSource.updateCartMenuCount(cartMenuModel.menuId,99)
        verify(
            cartDataSource.getCartMenusId().first().first().count == 99
        )
        assertEquals(
            expected, actual
        )
    }
}