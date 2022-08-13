package com.seom.banchan.ui.home.soupdish

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seom.banchan.R
import com.seom.banchan.data.api.SortCriteria
import com.seom.banchan.domain.model.*
import com.seom.banchan.domain.usecase.GetSoupMenusUseCase
import com.seom.banchan.ui.model.Model
import com.seom.banchan.ui.model.home.FilterMenuModel
import com.seom.banchan.ui.model.home.HeaderMenuModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SoupDishViewModel @Inject constructor(
    private val getSoupMenusUseCase: GetSoupMenusUseCase
) : ViewModel() {

    private val _soupDishMenus = MutableStateFlow<List<Model>>(emptyList())
    val soupDishMenus: StateFlow<List<Model>>
        get() = _soupDishMenus

    fun fetchSoupMenus() = viewModelScope.launch {
        getSoupMenusUseCase()
            .onSuccess { result ->
                _soupDishMenus.value = result.map { it.toHomeMenuGridModel() }
            }
            .onFailure {
                println(it)
            }
    }

    fun updateSort(sortCriteria: SortCriteria) {
        Log.d("sort Start",sortCriteria.name)
    }
}