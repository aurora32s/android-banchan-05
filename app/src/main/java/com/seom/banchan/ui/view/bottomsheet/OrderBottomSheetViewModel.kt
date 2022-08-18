package com.seom.banchan.ui.view.bottomsheet

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seom.banchan.ui.model.home.HomeMenuModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

class OrderBottomSheetViewModel(
) : ViewModel() {

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
            _totalPrice.emit(menuModel.menu.salePrice)
            _count.emit(menuModel.count)
        }
    }
}