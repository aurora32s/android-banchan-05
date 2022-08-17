package com.seom.banchan.ui.home.maindish

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seom.banchan.domain.model.home.MenuModel
import com.seom.banchan.domain.model.home.toHomeMenuLinearModel
import com.seom.banchan.domain.model.home.toHomeMenuModel
import com.seom.banchan.domain.usecase.GetMainMenusUseCase
import com.seom.banchan.ui.model.Model
import com.seom.banchan.ui.model.SortItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainDishViewModel @Inject constructor(
    private val getMainMenusUseCase: GetMainMenusUseCase
) : ViewModel() {

    private val _mainDishUiState = MutableStateFlow<MainDishUiState>(MainDishUiState())
    val mainDishUiState: StateFlow<MainDishUiState>
        get() = _mainDishUiState

    private val _toggleState = MutableStateFlow<ToggleState>(ToggleState.GRID)
    val toggleState: StateFlow<ToggleState>
        get() = _toggleState

    private var menuModels = emptyList<MenuModel>()

    fun fetchSortedMainMenus(sortItem: SortItem) = viewModelScope.launch {
        getMainMenusUseCase(sortItem.sortCriteria)
            .onSuccess { result ->
                menuModels = result
                _mainDishUiState.value = mainDishUiState.value.copy(
                    mainMenus = if (toggleState.value == ToggleState.LINEAR) result.map {
                        it.toHomeMenuLinearModel()
                    } else result.map {
                        it.toHomeMenuModel()
                    }
                )
            }
            .onFailure {
                println(it)
            }
    }

    fun updateToggle(toggle: ToggleState) {
        _toggleState.value = toggle
        _mainDishUiState.value = mainDishUiState.value.copy(
            mainMenus = if (toggle == ToggleState.LINEAR) menuModels.map {
                it.toHomeMenuLinearModel()
            } else menuModels.map {
                it.toHomeMenuModel()
            }
        )
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

