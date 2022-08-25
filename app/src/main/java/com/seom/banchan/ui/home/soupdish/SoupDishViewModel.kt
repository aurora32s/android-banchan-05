package com.seom.banchan.ui.home.soupdish

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seom.banchan.domain.model.home.MenuModel
import com.seom.banchan.domain.model.home.toHomeMenuModel
import com.seom.banchan.domain.usecase.GetCartMenusUseCase
import com.seom.banchan.domain.usecase.GetSoupMenusUseCase
import com.seom.banchan.ui.home.maindish.MainDishUiState
import com.seom.banchan.ui.model.Model
import com.seom.banchan.ui.model.SortItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SoupDishViewModel @Inject constructor(
    private val getSoupMenusUseCase: GetSoupMenusUseCase,
    getCartMenusUseCase: GetCartMenusUseCase
) : ViewModel() {

    private val _soupDishUiState = MutableStateFlow<SoupDishUiState>(SoupDishUiState.UnInitialized)
    val soupDishUiState = _soupDishUiState.asStateFlow()

    private val cartMenus = getCartMenusUseCase()
    private val _soupMenus = MutableStateFlow<List<MenuModel>>(emptyList())
    val soupMenus = _soupMenus
        .combine(cartMenus) { menus, carts ->
            menus.map { it.toHomeMenuModel(cartMenus = carts) }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(1000),
            initialValue = emptyList()
        )

    fun fetchSortedSoupMenus(sortItem: SortItem) = viewModelScope.launch {
        getSoupMenusUseCase(sortItem.sortCriteria)
            .onSuccess { result ->
                _soupMenus.value = result
                _soupDishUiState.value = SoupDishUiState.SuccessFetchMenus
            }
            .onFailure {
                _soupDishUiState.value = SoupDishUiState.FailFetchMenus
            }
    }
}

sealed interface SoupDishUiState {
    object UnInitialized : SoupDishUiState
    object SuccessFetchMenus : SoupDishUiState
    object FailFetchMenus : SoupDishUiState
}