package com.seom.banchan.ui.home.maindish

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seom.banchan.R
import com.seom.banchan.domain.model.*
import com.seom.banchan.domain.usecase.GetMainMenusUseCase
import com.seom.banchan.ui.home.sidedish.SideDishUiState
import com.seom.banchan.ui.model.Model
import com.seom.banchan.ui.model.Sort
import com.seom.banchan.ui.model.defaultSortItems
import com.seom.banchan.ui.model.selectedSortItem
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

    private val _toggleState = MutableStateFlow<ToggleState>(ToggleState())
    val toggleState: StateFlow<ToggleState>
        get() = _toggleState

    fun fetchSortedMainMenus(position : Int) = viewModelScope.launch {
        _mainDishUiState.value = mainDishUiState.value.copy(
            defaultSortItems = defaultSortItems().apply {
                this.selectedSortItem(position)
            },
            selectedSortPosition = position
        )
        getMainMenusUseCase(defaultSortItems().get(position).sortCriteria)
            .onSuccess { result ->
                _mainDishUiState.value = mainDishUiState.value.copy(
                    models = result,
                    mainMenus = if (toggleState.value.viewModeToggle) result.map {
                        it.toHomeMenuLinearModel()
                    } else result.map {
                        it.toHomeMenuGridModel()
                    }
                )
            }
            .onFailure {
                println(it)
            }
    }

    fun updateToggle(toggle : Boolean) {
        _toggleState.value = toggleState.value.copy(
            selectedTogglePosition = if(toggle) 1 else 0,
            viewModeToggle = toggle,
        )
    }

    fun updateViewMode(toggle : Boolean) {
        val models = mainDishUiState.value.models
        _mainDishUiState.value = mainDishUiState.value.copy(
            mainMenus = if (toggle) models.map {
                it.toHomeMenuLinearModel()
            } else models.map {
                it.toHomeMenuGridModel()
            }
        )
    }
}

data class MainDishUiState(
    val models : List<MenuModel> = emptyList(),
    val mainMenus : List<Model> = emptyList(),
    val defaultSortItems : List<Sort> = defaultSortItems(),
    val selectedSortPosition : Int = 0,
    val isLoading : Boolean = false, // TODO
    val error : String = "" // TODO
)

data class ToggleState(
    val selectedTogglePosition : Int = 0,
    val viewModeToggle : Boolean = false,
)