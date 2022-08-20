package com.seom.banchan.ui.order

import androidx.lifecycle.ViewModel
import com.seom.banchan.domain.model.order.toUiModel
import com.seom.banchan.domain.usecase.GetOrderListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class OrderListViewModel @Inject constructor(
    private val getOrderListUseCase: GetOrderListUseCase
) : ViewModel() {

    /**
     * 주문 내역 리스트 flow from database
     */
    fun orderList() = getOrderListUseCase().map {
        it.map { it.toUiModel() }
    }
}