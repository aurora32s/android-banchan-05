package com.seom.banchan.ui.home.soupdish

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seom.banchan.domain.model.toHomeMenuModel
import com.seom.banchan.R
import com.seom.banchan.data.api.SortCriteria
import com.seom.banchan.domain.model.*
import com.seom.banchan.domain.usecase.GetSoupMenusUseCase
import com.seom.banchan.ui.model.*
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

    fun fetchSortedSoupMenus(sortItem: SortItem) = viewModelScope.launch {
        getSoupMenusUseCase(sortItem.sortCriteria)
            .onSuccess { result ->
                _soupDishUiState.value = soupDishUiState.value.copy(
                    soupMenus = result.map { it.toHomeMenuModel() }
                )
            }
            .onFailure {
                println(it)
            }
    }
}

data class SoupDishUiState(
    val soupMenus : List<Model> = emptyList(),
    val isLoading : Boolean = false, // TODO
    val error : String = "" // TODO
)