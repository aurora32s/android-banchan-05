package com.seom.banchan.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seom.banchan.domain.model.cart.CartMenuModel
import com.seom.banchan.domain.model.detail.toUiModel
import com.seom.banchan.domain.model.home.MenuModel
import com.seom.banchan.domain.usecase.*
import com.seom.banchan.ui.model.detail.DetailMenuUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getMenuDetailUseCase: GetMenuDetailUseCase,
    private val addOrUpdateMenuToCartUseCase: AddOrUpdateMenuToCartUseCase,
    private val upsertRecentMenuUseCase: UpsertRecentMenuUseCase,
    getCartMenusUseCase: GetCartMenusUseCase,
    getOrderListUseCase: GetOrderListUseCase
) : ViewModel() {

    private val _detailUiState = MutableStateFlow<DetailUiState>(DetailUiState.UnInitialized)
    val detailUiState = _detailUiState.asStateFlow()

    // 상단 appbar 에 필요한 정보
    val cartMenus = getCartMenusUseCase() // 장바구니 메뉴 정보
    val orderList = getOrderListUseCase() // 주문 정보

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
    private val _currentMenu = MutableStateFlow<DetailMenuUiModel?>(null)
    val currentMenu = _currentMenu.asStateFlow()

    fun init() {
        _detailUiState.value = DetailUiState.UnInitialized
    }

    fun fetchData(menu: MenuModel?) {
        if (menu == null) {
            _detailUiState.value = DetailUiState.FailFetchDetail
            return
        }
        viewModelScope.launch {
            getMenuDetailUseCase(menu.id)
                .onSuccess {
                    _currentMenu.value = it.toUiModel(menu.name)
                    _totalPrice.value = it.salePrice
                    // 최근 본 상품으로 등록
                    upsertRecentMenuUseCase(menu)
                }
                .onFailure {
                    _detailUiState.value = DetailUiState.FailFetchDetail
                }
        }
    }

    fun addMenuToCart() {
        viewModelScope.launch {
            _detailUiState.value = DetailUiState.Loading
            _currentMenu.value?.let {
                val detailMenu = it.detailMenu
                val cartMenu = CartMenuModel(
                    menuId = detailMenu.id,
                    name = detailMenu.name,
                    image = detailMenu.images?.firstOrNull(),
                    salePrice = detailMenu.salePrice,
                    count = count.value,
                    selected = true // 기본은 선택된 상태
                )
                addOrUpdateMenuToCartUseCase(cartMenu)
                    .onSuccess {
                        _detailUiState.value = DetailUiState.FailAddToCart
//                        _detailUiState.value = DetailUiState.SuccessAddToCart
                    }
                    .onFailure {
                        _detailUiState.value = DetailUiState.FailAddToCart
                    }
            }
        }
    }

}

sealed interface DetailUiState {
    object UnInitialized : DetailUiState // 초기
    object Loading : DetailUiState
    object SuccessAddToCart : DetailUiState // 장바구니 추가 성송
    object FailFetchDetail : DetailUiState // 상세 정보 요청 실패
    object FailAddToCart : DetailUiState // 장바구니 추가 실패
}