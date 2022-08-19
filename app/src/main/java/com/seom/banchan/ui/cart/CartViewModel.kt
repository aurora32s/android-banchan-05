package com.seom.banchan.ui.cart

import androidx.lifecycle.ViewModel
import com.seom.banchan.domain.model.home.MenuModel
import com.seom.banchan.ui.model.cart.CartCheckModel
import com.seom.banchan.ui.model.cart.CartMenuModel
import com.seom.banchan.ui.model.cart.CartOrderModel
import com.seom.banchan.ui.model.cart.CartRecentModel
import com.seom.banchan.ui.model.home.HomeMenuModel
import com.seom.banchan.ui.model.order.OrderInfoModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor() : ViewModel() {

    private val _selectedCartItemIds = MutableStateFlow<List<String>>(emptyList())
    val selectedCartItemIds: StateFlow<List<String>>
        get() = _selectedCartItemIds

    private val _cartCheck = MutableStateFlow<CartCheckModel>(
        CartCheckModel( // 임시 데이터
            id = "cart_check",
            atLeastChecked = false,
            onAllCheck = { updateAllCheck() },
            onRemove = { removeItems() })
    )
    val cartCheck = _cartCheck.asStateFlow().combine(selectedCartItemIds) { cartCheck, ids ->
        CartCheckModel( // 임시 데이터
            id = "cart_check",
            atLeastChecked = selectedCartItemIds.value.isNotEmpty(),
            onAllCheck = {
                updateAllCheck()
            },
            onRemove = {
                removeItems()
            }
        )
    }

    private val _cartMenus = MutableStateFlow<MutableList<CartMenuModel>>(
        mutableListOf()
    )
    val cartMenus: StateFlow<MutableList<CartMenuModel>>
        get() = _cartMenus


    private val _orderInfo = MutableStateFlow<OrderInfoModel>(OrderInfoModel(orderPrice = 0))
    val orderInfo = _orderInfo.asStateFlow().combine(selectedCartItemIds) { orderInfo, ids ->
        OrderInfoModel(
            orderPrice = cartMenus.value.filter {
                ids.contains(it.id)
            }.sumOf {
                it.menu.salePrice * it.count
            }
        )
    }

    private val _cartOrder = MutableStateFlow<CartOrderModel>(CartOrderModel(totalPrice = 0))
    val cartOrder = _cartOrder.asStateFlow().combine(orderInfo) { _, orderInfo ->
        CartOrderModel(
            totalPrice = orderInfo.orderPrice
        )
    }

    private val _cartRecent = MutableStateFlow<CartRecentModel>(CartRecentModel(id = "cart_recent"))
    val cartRecent: StateFlow<CartRecentModel>
        get() = _cartRecent

    fun fetchCartMenus(){ // 테스트를 위한 코드. 원래는 로컬 DB에서 들고 온다
        val list = mutableListOf( // 임시 데이터
            CartMenuModel(
                id = "menu${System.currentTimeMillis()}",
                onCheck = {
                    updateCheck(it)
                },
                menu = homeMenuModel,
                onRemove = { removeItem(it) },
                onIncrease = { increaseCount(it) },
                onDecrease = { decreaseCount(it) }),
            CartMenuModel(
                id = "menu${System.currentTimeMillis()}",
                onCheck = {
                    updateCheck(it)
                },
                menu = homeMenuModel1,
                onRemove = { removeItem(it) },
                onIncrease = { increaseCount(it) },
                onDecrease = { decreaseCount(it) }),
            CartMenuModel(
                id = "menu${System.currentTimeMillis()}",
                onCheck = {
                    updateCheck(it)
                },
                menu = homeMenuModel2,
                onRemove = { removeItem(it) },
                onIncrease = { increaseCount(it) },
                onDecrease = { decreaseCount(it) })
        )
        _cartMenus.value = list
        _selectedCartItemIds.value = list.map {
            it.id
        }
    }


    fun updateCheck(cartMenuModel: CartMenuModel) {
        if (selectedCartItemIds.value.contains(cartMenuModel.id)) {
            _selectedCartItemIds.value = selectedCartItemIds.value.filter {
                it != cartMenuModel.id
            }
        } else {
            _selectedCartItemIds.value = selectedCartItemIds.value + listOf(cartMenuModel.id)
        }
        _cartMenus.value = cartMenus.value.apply {
            this.find {
                it.id == cartMenuModel.id
            }?.checked = !cartMenuModel.checked
        }
    }

    fun updateAllCheck() {
        if (selectedCartItemIds.value.isEmpty()) { // 선택된 상품이 없으면 전체선택하는 로직
            _selectedCartItemIds.value = cartMenus.value.map {
                it.id
            }
            _cartMenus.value = cartMenus.value.map {
                it.copy(
                    checked = true
                )
            }.toMutableList()
        } else { // 선택된 상품이 하나라도 있으면 모두 선택 해제하는 로직
            _selectedCartItemIds.value = emptyList()
            _cartMenus.value = cartMenus.value.map {
                it.copy(
                    checked = false
                )
            }.toMutableList()
        }
    }

    fun removeItem(cartMenuModel: CartMenuModel) {
        _cartMenus.value = cartMenus.value.filter {
            it.id != cartMenuModel.id
        }.toMutableList()
        _selectedCartItemIds.value = selectedCartItemIds.value.filter {
            it != cartMenuModel.id
        }
    }

    fun removeItems() {
        _cartMenus.value = cartMenus.value.filter {
            !selectedCartItemIds.value.contains(it.id)
        }.toMutableList()
        _selectedCartItemIds.value = emptyList()
    }

    fun increaseCount(cartMenuModel: CartMenuModel) {

    }

    fun decreaseCount(cartMenuModel: CartMenuModel) {

    }
}

private val homeMenuModel = MenuModel( // 임시 데이터
    id = "menu",
    normalPrice = 19300,
    salePrice = 17500,
    description = "",
    image = "http://public.codesquad.kr/jk/storeapp/data/main/349_ZIP_P_0024_T.jpg",
    deliveryType = listOf(),
    name = "소갈비찜"
)

private val homeMenuModel1 = MenuModel( // 임시 데이터
    id = "menu1",
    normalPrice = 0,
    salePrice = 11800,
    description = "",
    image = "http://public.codesquad.kr/jk/storeapp/data/main/739_ZIP_P__T.jpg",
    deliveryType = listOf(),
    name = "초계국수"
)

private val homeMenuModel2 = MenuModel( // 임시 데이터
    id = "menu2",
    normalPrice = 0,
    salePrice = 16900,
    description = "",
    image = "http://public.codesquad.kr/jk/storeapp/data/main/510_ZIP_P_0047_T.jpg",
    deliveryType = listOf(),
    name = "쭈꾸미 한돈 제육볶음"
)