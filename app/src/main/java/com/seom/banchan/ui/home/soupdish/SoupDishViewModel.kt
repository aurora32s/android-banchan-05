package com.seom.banchan.ui.home.soupdish

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seom.banchan.R
import com.seom.banchan.data.api.SortCriteria
import com.seom.banchan.domain.model.*
import com.seom.banchan.domain.usecase.GetSoupMenusUseCase
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
class SoupDishViewModel @Inject constructor(
    private val getSoupMenusUseCase: GetSoupMenusUseCase
) : ViewModel() {


    private val _soupDishUiState = MutableStateFlow<SoupDishUiState>(SoupDishUiState())
    val soupDishUiState: StateFlow<SoupDishUiState>
        get() = _soupDishUiState

    fun fetchSortedSoupMenus(position:Int) = viewModelScope.launch {
        _soupDishUiState.value = soupDishUiState.value.copy(
            defaultSortItems = defaultSortItems().apply {
                this.selectedSortItem(position)
            },
            selectedSortPosition = position
        )
        getSoupMenusUseCase(defaultSortItems().get(position).sortCriteria)
            .onSuccess { result ->
                _soupDishUiState.value = soupDishUiState.value.copy(
                    soupMenus = result.map { it.toHomeMenuGridModel() }
                )
            }
            .onFailure {
                println(it)
            }
    }
}

data class SoupDishUiState(
    val soupMenus : List<Model> = emptyList(),
    val defaultSortItems : List<Sort> = defaultSortItems(),
    val selectedSortPosition : Int = 0,
    val isLoading : Boolean = false, // TODO
    val error : String = "" // TODO
)