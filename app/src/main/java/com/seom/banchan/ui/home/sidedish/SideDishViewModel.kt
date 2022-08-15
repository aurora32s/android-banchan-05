package com.seom.banchan.ui.home.sidedish

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seom.banchan.R
import com.seom.banchan.domain.model.*
import com.seom.banchan.domain.model.home.toHomeMenuGridModel
import com.seom.banchan.domain.usecase.GetSideMenusUseCase
import com.seom.banchan.domain.usecase.GetSoupMenusUseCase
import com.seom.banchan.ui.model.Model
import com.seom.banchan.ui.model.home.FilterMenuModel
import com.seom.banchan.ui.model.home.HeaderMenuModel
import com.seom.banchan.ui.model.home.TotalMenuModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SideDishViewModel @Inject constructor(
    private val getSideMenusUseCase: GetSideMenusUseCase
) : ViewModel() {

    private val _sideDishMenus = MutableStateFlow<List<Model>>(emptyList())
    val sideDishMenus: StateFlow<List<Model>>
        get() = _sideDishMenus

    fun fetchSideMenus() = viewModelScope.launch {
        getSideMenusUseCase()
            .onSuccess { result ->
                _sideDishMenus.value = result.map { it.toHomeMenuGridModel() }
            }
            .onFailure {
                println(it)
            }
    }
}