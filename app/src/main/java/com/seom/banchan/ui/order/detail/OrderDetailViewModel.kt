package com.seom.banchan.ui.order.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seom.banchan.domain.model.order.OrderModel
import com.seom.banchan.domain.model.order.toUiModel
import com.seom.banchan.domain.usecase.GetDetailOrderInfoUseCase
import com.seom.banchan.ui.model.order.OrderDeliveryState
import com.seom.banchan.ui.model.order.OrderInfoModel
import com.seom.banchan.ui.model.order.OrderMenuUiModel
import com.seom.banchan.ui.model.order.OrderStateUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class OrderDetailViewModel @Inject constructor(
    private val getDetailOrderInfoUseCase: GetDetailOrderInfoUseCase
) : ViewModel() {

    private val _orderDetailUiState =
        MutableStateFlow<OrderDetailUiState>(OrderDetailUiState.UnInitialized)
    val orderDetailUiState = _orderDetailUiState.asStateFlow()

    // 주문 배달 상태
    private val _orderDeliveryState = MutableStateFlow(OrderDeliveryState.DELIVERING)
    private val orderDeliveryState = _orderDeliveryState.asStateFlow()

    /**
     * 남은 배달 시간
     */
    private val _extraTime = MutableStateFlow(0L)
    private val extraTime = _extraTime.asStateFlow()

    /**
     * 남은 시간 계산
     */
    private val timer = Timer()
    private val timerTask = object : TimerTask() {
        override fun run() {
            _extraTime.value = _extraTime.value - 1000
            if (_extraTime.value <= 0) {
                timer.cancel()
            }
        }
    }

    fun fetchData(orderId: Long) {
        _orderDetailUiState.value = OrderDetailUiState.Loading
        viewModelScope.launch {
            getDetailOrderInfoUseCase(orderId)
                .onSuccess { orderDetail ->
                    val order: OrderModel = orderDetail.order.first()
                    val orderMenus = orderDetail.menus.map { it.toUiModel() }
                    val menuCount = orderMenus.sumOf { it.count }

                    _extraTime.value =
                        order.expectedTime - (System.currentTimeMillis() - order.createdAt)

                    if (_extraTime.value > 0) {
                        timer.schedule(timerTask, 0, 1000)
                    }
                    val orderState = OrderStateUiModel(
                        orderDeliveryState = orderDeliveryState,
                        createdAt = order.createdAt,
                        extraTime = extraTime,
                        expectedDeliveryTime = order.expectedTime,
                        menuCount = menuCount
                    )

                    val orderInfo = OrderInfoModel(
                        orderPrice = orderMenus.sumOf { it.salePrice * it.count }
                    )

                    _orderDetailUiState.value = OrderDetailUiState.Success(
                        order = orderState,
                        menus = orderMenus,
                        orderInfo = orderInfo
                    )

                    orderDetail.order
                        .catch {
                            _orderDetailUiState.value = OrderDetailUiState.Error
                        }
                        .collect {
                            _orderDeliveryState.value = it.deliveryState
                        }
                }
                .onFailure {
                    _orderDetailUiState.value = OrderDetailUiState.Error
                }
        }
    }
}

sealed interface OrderDetailUiState {
    object UnInitialized : OrderDetailUiState
    object Loading : OrderDetailUiState

    data class Success(
        val order: OrderStateUiModel,
        val menus: List<OrderMenuUiModel>,
        val orderInfo: OrderInfoModel
    ) : OrderDetailUiState

    object Error : OrderDetailUiState
}