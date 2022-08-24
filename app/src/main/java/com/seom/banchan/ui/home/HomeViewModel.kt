package com.seom.banchan.ui.home

import androidx.lifecycle.ViewModel
import com.seom.banchan.domain.model.cart.CartMenuModel
import com.seom.banchan.domain.model.order.OrderListModel
import com.seom.banchan.domain.usecase.GetCartMenusUseCase
import com.seom.banchan.domain.usecase.GetOrderListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getCartMenusUseCase: GetCartMenusUseCase,
    private val getOrderListUseCase: GetOrderListUseCase
) : ViewModel() {

    private val _cartMenus = getCartMenusUseCase()
    val cartMenus : Flow<List<CartMenuModel>>
        get() = _cartMenus


    private val _orderList = getOrderListUseCase()
    val orderList : Flow<List<OrderListModel>>
        get() = _orderList
}