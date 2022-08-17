package com.seom.banchan.ui.detail

import androidx.databinding.ObservableInt
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seom.banchan.domain.model.cart.CartMenuModel
import com.seom.banchan.domain.model.detail.DetailMenuModel
import com.seom.banchan.domain.model.detail.toUiModel
import com.seom.banchan.domain.model.home.MenuModel
import com.seom.banchan.domain.usecase.AddMenuToCartUseCase
import com.seom.banchan.domain.usecase.GetMenuDetailUseCase
import com.seom.banchan.ui.model.detail.DetailMenuUiModel
import com.seom.banchan.ui.model.detail.MenuDetailModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getMenuDetailUseCase: GetMenuDetailUseCase,
    private val addMenuToCartUseCase: AddMenuToCartUseCase
) : ViewModel() {
    private val _detailMenuUiModel = MutableStateFlow<DetailUiState>(DetailUiState.UnInitialized)
    val detailMenuModel: StateFlow<DetailUiState>
        get() = _detailMenuUiModel

    // 선택한 음식의 개수
    private val _count = MutableStateFlow(1)
    val count = _count.asStateFlow()

    fun increaseCount() = viewModelScope.launch {
        _count.emit(_count.value + 1)
    }

    fun decreaseCount() = viewModelScope.launch {
        if (_count.value == 1) return@launch
        _count.emit(_count.value - 1)
    }

    // 음식의 전체 가격
    private val _totalPrice = MutableStateFlow(0)
    val totalPrice =
        _totalPrice.asStateFlow().combine(count) { newCount, salePrice -> newCount * salePrice }

    // 현재 음식 data
    lateinit var currentMenu: DetailMenuModel

    fun fetchData(menu: MenuModel?) = viewModelScope.launch {
        if (menu == null) {
            _detailMenuUiModel.value = DetailUiState.Error
            return@launch
        }
        getMenuDetailUseCase(menu.id)
            .onSuccess {
                currentMenu = it
                _totalPrice.value = it.salePrice
                _detailMenuUiModel.value = DetailUiState.Success.SuccessFetch(currentMenu.toUiModel())
            }
    }

    fun addMenuToCart() = viewModelScope.launch {
        val cartMenu = CartMenuModel(
            menuId = currentMenu.id,
            name = currentMenu.name,
            image = currentMenu.thumbnail?.firstOrNull(),
            salePrice = currentMenu.salePrice,
            count = count.value
        )
        addMenuToCartUseCase(cartMenu)
            .onSuccess {
                _detailMenuUiModel.value = DetailUiState.Success.SuccessAddToCart
            }
    }

}

sealed interface DetailUiState {
    object UnInitialized : DetailUiState
    sealed interface Success: DetailUiState {
        data class SuccessFetch(
            val detailMenu: DetailMenuUiModel
        ) : Success
        object SuccessAddToCart: Success
    }

    object Error : DetailUiState
}