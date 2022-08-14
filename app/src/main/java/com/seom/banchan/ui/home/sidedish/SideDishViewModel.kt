package com.seom.banchan.ui.home.sidedish

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seom.banchan.R
import com.seom.banchan.domain.model.*
import com.seom.banchan.domain.usecase.GetSideMenusUseCase
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
class SideDishViewModel @Inject constructor(
    private val getSideMenusUseCase: GetSideMenusUseCase
) : ViewModel() {

    private val _sideDishUiState = MutableStateFlow<SideDishUiState>(SideDishUiState())
    val sideDishUiState: StateFlow<SideDishUiState>
        get() = _sideDishUiState

    fun fetchSortedSideMenus(position:Int) = viewModelScope.launch {
        _sideDishUiState.value = sideDishUiState.value.copy(
            defaultSortItems = defaultSortItems().apply {
                this.selectedSortItem(position)
            },
            selectedSortPosition = position
        )
        getSideMenusUseCase(defaultSortItems().get(position).sortCriteria)
            .onSuccess { result ->
                _sideDishUiState.value = sideDishUiState.value.copy(
                    sideMenus = result.map { it.toHomeMenuGridModel() }
                )
            }
            .onFailure {
                println(it)
            }
    }
}

data class SideDishUiState(
    val sideMenus : List<Model> = emptyList(),
    val defaultSortItems : List<Sort> = defaultSortItems(),
    val selectedSortPosition : Int = 0,
    val isLoading : Boolean = false, // TODO
    val error : String = "" // TODO
)