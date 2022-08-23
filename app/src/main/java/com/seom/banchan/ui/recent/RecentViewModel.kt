package com.seom.banchan.ui.recent

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seom.banchan.domain.model.home.MenuModel
import com.seom.banchan.domain.model.home.toHomeMenuModel
import com.seom.banchan.domain.usecase.GetCartMenusIdUseCase
import com.seom.banchan.domain.usecase.GetRecentMenusUseCase
import com.seom.banchan.ui.model.CellType
import com.seom.banchan.ui.model.home.HomeMenuModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecentViewModel @Inject constructor(
    private val getRecentMenusUseCase: GetRecentMenusUseCase,
    getCartMenusIdUseCase: GetCartMenusIdUseCase
) : ViewModel() {

    private val cartMenus = getCartMenusIdUseCase()
    private val _recentMenus = MutableStateFlow<List<MenuModel>>(emptyList())
    val recentMenus = _recentMenus
        .combine(cartMenus) { menus, carts ->
            menus.map {
                it.toHomeMenuModel(
                    cartMenus = carts,
                    cellType = CellType.MENU_RECENT_CELL
                )
            }
        }

    fun getRecentMenus() {
        viewModelScope.launch {
            getRecentMenusUseCase().collectLatest {
                _recentMenus.value = it
            }
        }
    }
}
