package com.seom.banchan.ui.home.sidedish

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seom.banchan.domain.model.home.toHomeMenuModel
import com.seom.banchan.domain.usecase.GetSideMenusUseCase
import com.seom.banchan.ui.model.Model
import com.seom.banchan.ui.model.SortItem
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

    fun fetchSortedSideMenus(sortItem: SortItem) = viewModelScope.launch {
        getSideMenusUseCase(sortItem.sortCriteria)
            .onSuccess { result ->
                _sideDishUiState.value = sideDishUiState.value.copy(
                    sideMenus = result.map { it.toHomeMenuModel() }
                )
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