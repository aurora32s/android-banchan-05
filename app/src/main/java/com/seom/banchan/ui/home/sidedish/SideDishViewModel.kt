package com.seom.banchan.ui.home.sidedish

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seom.banchan.domain.model.home.MenuModel
import com.seom.banchan.domain.model.home.toHomeMenuModel
import com.seom.banchan.domain.usecase.GetCartMenusUseCase
import com.seom.banchan.domain.usecase.GetSideMenusUseCase
import com.seom.banchan.ui.home.soupdish.SoupDishUiState
import com.seom.banchan.ui.model.Model
import com.seom.banchan.ui.model.SortItem
import com.seom.banchan.ui.model.home.HomeMenuModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SideDishViewModel @Inject constructor(
    private val getSideMenusUseCase: GetSideMenusUseCase,
    getCartMenusUseCase: GetCartMenusUseCase
) : ViewModel() {

    private val _sideDishUiState = MutableStateFlow<SideDishUiState>(SideDishUiState.UnInitialized)
    val sideDishUiState = _sideDishUiState.asStateFlow()

    private val cartMenus = getCartMenusUseCase()
    private val _sideMenus = MutableStateFlow<List<MenuModel>>(emptyList())
    val sideMenus = _sideMenus
        .combine(cartMenus) { menus, carts ->
            menus.map { it.toHomeMenuModel(cartMenus = carts) }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(1000),
            initialValue = emptyList()
        )

    fun fetchSortedSideMenus(sortItem: SortItem) = viewModelScope.launch {
        getSideMenusUseCase(sortItem.sortCriteria)
            .onSuccess { result ->
                _sideMenus.value = result
                _sideDishUiState.value = SideDishUiState.SuccessFetchMenus
            }
            .onFailure {
                _sideDishUiState.value = SideDishUiState.FailFetchMenus
            }
    }
}

sealed interface SideDishUiState {
    object UnInitialized : SideDishUiState
    object SuccessFetchMenus : SideDishUiState
    object FailFetchMenus : SideDishUiState
}