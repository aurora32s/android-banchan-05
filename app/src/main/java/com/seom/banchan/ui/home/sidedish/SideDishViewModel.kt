package com.seom.banchan.ui.home.sidedish

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seom.banchan.domain.model.home.MenuModel
import com.seom.banchan.domain.model.home.toHomeMenuModel
import com.seom.banchan.domain.usecase.GetCartMenusUseCase
import com.seom.banchan.domain.usecase.GetSideMenusUseCase
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
//    private val _sideDishUiState = MutableStateFlow(SideDishUiState())
//    val sideDishUiState: StateFlow<SideDishUiState>
//        get() = _sideDishUiState

    fun fetchSortedSideMenus(sortItem: SortItem) = viewModelScope.launch {
        getSideMenusUseCase(sortItem.sortCriteria)
            .onSuccess { result ->
                _sideMenus.value = result
            }
            .onFailure {
                println(it)
            }
    }
}

data class SideDishUiState(
    val sideMenus: List<Model> = emptyList(),
    val isLoading: Boolean = false, // TODO
    val error: String = "" // TODO
)