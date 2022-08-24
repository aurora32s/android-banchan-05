package com.seom.banchan.ui.order

import android.util.Log
import androidx.lifecycle.ViewModel
import com.seom.banchan.domain.model.order.toUiModel
import com.seom.banchan.domain.usecase.GetOrderListUseCase
import com.seom.banchan.util.exception.DatabaseFlowException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class OrderListViewModel @Inject constructor(
    private val getOrderListUseCase: GetOrderListUseCase
) : ViewModel() {
    /**
     * 주문 내역 리스트 flow from database
     */
    fun orderList() = getOrderListUseCase().map { orders ->
        orders.map { order -> order.toUiModel() }
    }.catch {
        Log.e(OrderListFragment.TAG, this.toString())
        throw DatabaseFlowException(it.toString())
    }
}