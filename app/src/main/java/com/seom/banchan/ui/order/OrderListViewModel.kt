package com.seom.banchan.ui.order

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seom.banchan.data.source.OrderDataSource
import com.seom.banchan.domain.usecase.GetOrderListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrderListViewModel @Inject constructor(
    private val getOrderListUseCase: GetOrderListUseCase,
    private val orderDataSource: OrderDataSource
) : ViewModel() {

    /**
     * 주문 내역 리스트 flow from database
     */
    fun orderList() = getOrderListUseCase()
}