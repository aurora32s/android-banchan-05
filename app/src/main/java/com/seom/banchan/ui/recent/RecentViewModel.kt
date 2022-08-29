package com.seom.banchan.ui.recent

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import com.seom.banchan.domain.model.home.toHomeMenuModel
import com.seom.banchan.domain.usecase.GetCartMenusUseCase
import com.seom.banchan.domain.usecase.GetRecentMenusPagingUseCase
import com.seom.banchan.ui.model.CellType
import com.seom.banchan.ui.model.home.HomeMenuModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class RecentViewModel @Inject constructor(
    getRecentMenusPagingUseCase: GetRecentMenusPagingUseCase,
    getCartMenusUseCase: GetCartMenusUseCase
) : ViewModel() {

    private val cartMenus = getCartMenusUseCase()
    private val _recentMenus = getRecentMenusPagingUseCase().cachedIn(viewModelScope)

    val recentMenus = _recentMenus
        .combine(cartMenus) { menus, carts ->
            menus.map {
                it.toHomeMenuModel(
                    cartMenus = carts,
                    cellType = CellType.MENU_RECENT_CELL
                )
            }
        }
}
