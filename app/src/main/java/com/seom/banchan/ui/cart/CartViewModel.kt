package com.seom.banchan.ui.cart

import androidx.lifecycle.ViewModel
import com.seom.banchan.ui.model.cart.CartCheckModel
import com.seom.banchan.ui.model.cart.CartMenuModel
import com.seom.banchan.ui.model.cart.CartOrderModel
import com.seom.banchan.ui.model.cart.CartRecentModel
import com.seom.banchan.ui.model.order.OrderInfoModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor() : ViewModel() {
    private val _cartCheck = MutableStateFlow<CartCheckModel>(CartCheckModel(id = "cart_check"))
    val cartCheck : StateFlow<CartCheckModel>
        get() = _cartCheck

    private val _cartMenus = MutableStateFlow<List<CartMenuModel>>(emptyList())
    val cartMenus : StateFlow<List<CartMenuModel>>
        get() = _cartMenus

    private val _orderInfo = MutableStateFlow<OrderInfoModel>(OrderInfoModel(id = "order_info"))
    val orderInfo : StateFlow<OrderInfoModel>
        get() = _orderInfo

    private val _cartOrder = MutableStateFlow<CartOrderModel>(CartOrderModel(id = "cart_order"))
    val cartOrder : StateFlow<CartOrderModel>
        get() = _cartOrder

    private val _cartRecent = MutableStateFlow<CartRecentModel>(CartRecentModel(id = "cart_recent"))
    val cartRecent : StateFlow<CartRecentModel>
        get() = _cartRecent

    val cartUi = flowOf(cartCheck,cartOrder,cartMenus,orderInfo,cartRecent)
    init {


    }

}

data class CartUiState(
    val uiState : MutableStateFlow<Boolean>
)