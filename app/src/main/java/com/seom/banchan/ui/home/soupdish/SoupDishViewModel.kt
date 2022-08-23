package com.seom.banchan.ui.home.soupdish

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seom.banchan.domain.model.home.MenuModel
import com.seom.banchan.domain.model.home.toHomeMenuModel
import com.seom.banchan.domain.usecase.GetCartMenusIdUseCase
import com.seom.banchan.domain.usecase.GetSoupMenusUseCase
import com.seom.banchan.ui.model.Model
import com.seom.banchan.ui.model.SortItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SoupDishViewModel @Inject constructor(
    private val getSoupMenusUseCase: GetSoupMenusUseCase,
    getCartMenusIdUseCase: GetCartMenusIdUseCase
) : ViewModel() {

    private val cartMenus = getCartMenusIdUseCase()
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
//    private val _soupDishUiState = MutableStateFlow<SoupDishUiState>(SoupDishUiState())
//    val soupDishUiState: StateFlow<SoupDishUiState>
//        get() = _soupDishUiState

    fun fetchSortedSoupMenus(sortItem: SortItem) = viewModelScope.launch {
        getSoupMenusUseCase(sortItem.sortCriteria)
            .onSuccess { result ->
                _soupMenus.value = result
            }
            .onFailure {
                println(it)
            }
    }
}

data class SoupDishUiState(
    val soupMenus: List<Model> = emptyList(),
    val isLoading: Boolean = false, // TODO
    val error: String = "" // TODO
)