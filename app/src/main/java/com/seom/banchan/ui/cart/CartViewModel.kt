package com.seom.banchan.ui.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seom.banchan.domain.model.cart.toUiModel
import com.seom.banchan.domain.model.home.MenuModel
import com.seom.banchan.domain.model.home.toHomeMenuModel
import com.seom.banchan.domain.usecase.*
import com.seom.banchan.ui.model.CellType
import com.seom.banchan.ui.model.cart.CartCheckModel
import com.seom.banchan.ui.model.cart.CartMenuUiModel
import com.seom.banchan.ui.model.cart.CartOrderModel
import com.seom.banchan.ui.model.cart.CartRecentModel
import com.seom.banchan.ui.model.order.OrderInfoModel
import com.seom.banchan.util.TimeUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val getCartMenusIdUseCase: GetCartMenusIdUseCase,
    private val deleteCartMenuListUseCase: DeleteCartMenuListUseCase,
    private val deleteCartMenuUseCase: DeleteCartMenuUseCase,
    private val updateCartMenuListSelectedUseCase: UpdateCartMenuListSelectedUseCase,
    private val updateCartMenuSelectedUseCase: UpdateCartMenuSelectedUseCase,
    private val updateCartMenuCountIncreaseUseCase: UpdateCartMenuCountIncreaseUseCase,
    private val updateCartMenuCountDecreaseUseCase: UpdateCartMenuCountDecreaseUseCase,
    private val updateCartMenuCountUseCase: UpdateCartMenuCountUseCase,
    private val getRecentMenusUseCase: GetRecentMenusUseCase
) : ViewModel() {

    init {
        fetchRecentMenus()
        fetchCartMenus()
    }

    private val _cartMenus = MutableStateFlow<List<CartMenuUiModel>>(
        mutableListOf()
    )
    val cartMenus: StateFlow<List<CartMenuUiModel>>
        get() = _cartMenus

    private val _cartCheck = MutableStateFlow<CartCheckModel>(
        CartCheckModel()
    )
    val cartCheck = _cartCheck.asStateFlow()
        .combine(cartMenus) { _, menus ->
            CartCheckModel(
                atLeastChecked = menus.any { // List<*>.any -> 조건을 만족하는 원소가 1개 이상 존재하면 true
                    it.checked
                }
            )
        }

    private val _orderInfo = MutableStateFlow<OrderInfoModel>(OrderInfoModel())
    val orderInfo = _orderInfo.asStateFlow()
        .combine(cartMenus) { _, menus ->
            OrderInfoModel(
                orderPrice = menus.filter {
                    it.checked
                }.sumOf {
                    it.menu.salePrice * it.count
                }
            )
        }

    private val _cartOrder = MutableStateFlow<CartOrderModel>(CartOrderModel(totalPrice = 0))
    val cartOrder = _cartOrder.asStateFlow()
        .combine(orderInfo) { _, info ->
            CartOrderModel(
                totalPrice = info.orderPrice
            )
        }

    private val _cartRecent = MutableStateFlow<CartRecentModel>(
        CartRecentModel()
    )
    val cartRecent: StateFlow<CartRecentModel>
        get() = _cartRecent

    private val _orderEventFlow = MutableStateFlow(-1L)
    val orderEventFlow : StateFlow<Long>
        get() = _orderEventFlow

    private fun fetchCartMenus() {
        viewModelScope.launch {
            getCartMenusIdUseCase().collectLatest { list ->
                _cartMenus.value = list.map {
                    it.toUiModel()
                }
            }
        }
    }

    fun updateCheck(cartMenuUiModel: CartMenuUiModel) {
        viewModelScope.launch {
            updateCartMenuSelectedUseCase(
                cartMenuUiModel.menu.id
            )
        }
    }

    fun updateAllCheck() {
        viewModelScope.launch {
            updateCartMenuListSelectedUseCase(
                cartMenus.value.filter { cartMenu ->
                    if (cartMenus.value.any { it.checked }) { // 하나라도 선택된 게 있으면 선택된 메뉴들 선택 해제 로직
                        cartMenu.checked
                    } else { // 하나도 없으면 전체 선택
                        !cartMenu.checked
                    }
                }.map {
                    it.menu.id
                }
            )
        }
    }

    fun removeItem(cartMenuUiModel: CartMenuUiModel) {
        viewModelScope.launch {
            deleteCartMenuUseCase(
                cartMenuUiModel.menu.id
            )
        }
    }

    fun removeItems() {
        viewModelScope.launch {
            deleteCartMenuListUseCase(
                cartMenus.value.filter {
                    it.checked
                }.map {
                    it.menu.id
                }
            )
        }
    }

    fun increaseCount(cartMenuUiModel: CartMenuUiModel) {
        if (cartMenuUiModel.count < 99) {
            viewModelScope.launch {
                updateCartMenuCountIncreaseUseCase(
                    cartMenuUiModel.menu.id
                )
            }
        }
    }

    fun decreaseCount(cartMenuUiModel: CartMenuUiModel) {
        if (cartMenuUiModel.count > 1) {
            viewModelScope.launch {
                updateCartMenuCountDecreaseUseCase(
                    cartMenuUiModel.menu.id
                )
            }
        }
    }

    fun updateCount(cartMenuUiModel: CartMenuUiModel, count : Int){
        viewModelScope.launch {
            updateCartMenuCountUseCase(
                menuId = cartMenuUiModel.menu.id,
                count = count
            )
        }
    }

    fun insertOrderAndRemoveCartMenus(){
        viewModelScope.launch {
            cartMenus.value.filter {
                it.checked
            }
            // TODO 주문하는 유즈케이스
            // 주문 성공하면 카트에서 메뉴 삭제
            removeItems()
            _orderEventFlow.value = 0L // order insert 이후 받은 orderId 값
        }
    }

    private fun fetchRecentMenus() {
        viewModelScope.launch {
            getRecentMenusUseCase(isLatest = true).collectLatest { list ->
                _cartRecent.value = cartRecent.value.copy(
                    recentMenus = list
                        .map { menuModel ->
                            menuModel.toHomeMenuModel(cellType = CellType.CART_MENU_RECENT_CELL)
                        }
                )
            }
        }
    }
}