package com.seom.banchan.ui.view.bottomsheet

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seom.banchan.domain.model.cart.CartMenuModel
import com.seom.banchan.domain.usecase.AddOrReplaceMenuToCartUseCase
import com.seom.banchan.ui.model.home.HomeMenuModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrderBottomSheetViewModel @Inject constructor(
    private val addOrReplaceMenuToCartUseCase: AddOrReplaceMenuToCartUseCase
) : ViewModel() {
    private var currentMenu: HomeMenuModel? = null

    private val _orderBottomSheetUiState =
        MutableStateFlow<OrderBottomSheetUiState>(OrderBottomSheetUiState.UnInitialized)
    val orderBottomSheetUiState: StateFlow<OrderBottomSheetUiState>
        get() = _orderBottomSheetUiState

    // 선택한 음식의 개수
    private val _count = MutableStateFlow(0)
    val count = _count.asStateFlow()

    fun increaseCount() = viewModelScope.launch {
        _count.emit(_count.value + 1)
    }

    fun decreaseCount() = viewModelScope.launch {
        if (_count.value == 1) return@launch
        _count.emit(_count.value - 1)
    }

    private val _totalPrice = MutableStateFlow(0)
    val totalPrice =
        _totalPrice.asStateFlow().combine(count) { newCount, salePrice -> newCount * salePrice }

    fun init(menuModel: HomeMenuModel?) = viewModelScope.launch {
        menuModel?.let {
            currentMenu = it
            _totalPrice.emit(menuModel.menu.salePrice)
            _count.emit(menuModel.count)
        }
    }

    fun addMenuToCart() = viewModelScope.launch {
        currentMenu?.menu?.let {
            val cartMenu = CartMenuModel(
                menuId = it.id,
                name = it.name,
                image = it.image,
                salePrice = it.salePrice,
                count = count.value,
                selected = true // 기본은 선택된 상태
            )
            addOrReplaceMenuToCartUseCase(cartMenu)
                .onSuccess {
                    _orderBottomSheetUiState.value = OrderBottomSheetUiState.SuccessAddToCart
                }
        }
    }
}

sealed interface OrderBottomSheetUiState {
    object UnInitialized : OrderBottomSheetUiState
    object SuccessAddToCart : OrderBottomSheetUiState
}