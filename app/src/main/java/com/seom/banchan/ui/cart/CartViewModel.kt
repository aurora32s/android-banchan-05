package com.seom.banchan.ui.cart

import androidx.lifecycle.ViewModel
import com.seom.banchan.ui.model.cart.CartCheckModel
import com.seom.banchan.ui.model.cart.CartMenuModel
import com.seom.banchan.ui.model.cart.CartOrderModel
import com.seom.banchan.ui.model.cart.CartRecentModel
import com.seom.banchan.ui.model.order.OrderInfoModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor() : ViewModel() {


    private val _cartCheck = MutableStateFlow<CartCheckModel>(CartCheckModel(id = "cart_check"))
    val cartCheck: StateFlow<CartCheckModel>
        get() = _cartCheck

    private val _cartMenus = MutableStateFlow<List<CartMenuModel>>(emptyList())
    val cartMenus : StateFlow<List<CartMenuModel>>
        get() = _cartMenus

    private val _orderInfo = MutableStateFlow<OrderInfoModel>(OrderInfoModel(orderPrice = 0))
    val orderInfo = _orderInfo.asStateFlow().combine(cartMenus) { orderInfo, cartMenus ->
        OrderInfoModel(
            orderPrice = cartMenus.sumOf {
                it.price
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



    fun updateCheck(cartMenuModel : CartMenuModel) {

    }

}
