package com.seom.banchan.ui.home.maindish

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seom.banchan.domain.model.home.MenuModel
import com.seom.banchan.domain.model.home.toHomeMenuModel
import com.seom.banchan.domain.usecase.GetCartMenusIdUseCase
import com.seom.banchan.domain.usecase.GetMainMenusUseCase
import com.seom.banchan.ui.model.CellType
import com.seom.banchan.ui.model.Model
import com.seom.banchan.ui.model.SortItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainDishViewModel @Inject constructor(
    private val getMainMenusUseCase: GetMainMenusUseCase,
    getCartMenusIdUseCase: GetCartMenusIdUseCase
) : ViewModel() {

//    private val _mainDishUiState = MutableStateFlow<MainDishUiState>(MainDishUiState())
//    val mainDishUiState: StateFlow<MainDishUiState>
//        get() = _mainDishUiState

    private val _toggleState = MutableStateFlow(ToggleState.GRID)
    val toggleState: StateFlow<ToggleState>
        get() = _toggleState

    private val cartMenus = getCartMenusIdUseCase()
    private val _mainMenus = MutableStateFlow<List<MenuModel>>(emptyList())
    val mainMenus = _mainMenus
        .combine(_toggleState) { menus, _ -> menus }
        .combine(cartMenus) { menus, carts ->
            menus.map {
                it.toHomeMenuModel(
                    cartMenus = carts,
                    cellType = when (toggleState.value) {
                        ToggleState.LINEAR -> CellType.MENU_LARGE_CELL
                        ToggleState.GRID -> CellType.MENU_CELL
                    }
                )
            }
        }

    private var menuModels = emptyList<MenuModel>()

    fun fetchSortedMainMenus(sortItem: SortItem) = viewModelScope.launch {
        getMainMenusUseCase(sortItem.sortCriteria)
            .onSuccess { result ->
                menuModels = result
                _mainMenus.value = result
            }
            .onFailure {
                println(it)
            }
    }

    fun updateToggle(toggle: ToggleState) {
        _toggleState.value = toggle
    }
}

data class MainDishUiState(
    val mainMenus: List<Model> = emptyList(),
    val isLoading: Boolean = false, // TODO
    val error: String = "" // TODO
)

enum class ToggleState(val spanCount: Int) {
    LINEAR(1),
    GRID(2)
}

