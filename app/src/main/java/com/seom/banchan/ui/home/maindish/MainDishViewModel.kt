package com.seom.banchan.ui.home.maindish

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seom.banchan.R
import com.seom.banchan.domain.model.*
import com.seom.banchan.domain.model.home.MenuModel
import com.seom.banchan.domain.model.home.toHomeMenuGridModel
import com.seom.banchan.domain.model.home.toHomeMenuLinearModel
import com.seom.banchan.domain.usecase.GetMainMenusUseCase
import com.seom.banchan.domain.usecase.GetMenuWithCategoriesUseCase
import com.seom.banchan.ui.model.Model
import com.seom.banchan.ui.model.home.FilterMenuModel
import com.seom.banchan.ui.model.home.HeaderMenuModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainDishViewModel @Inject constructor(
    private val getMainMenusUseCase: GetMainMenusUseCase
) : ViewModel() {
    private val _rowData = MutableStateFlow<List<MenuModel>>(emptyList())
    private val rowData: StateFlow<List<MenuModel>>
        get() = _rowData

    private val _mainDishMenus = MutableStateFlow<List<Model>>(emptyList())
    val mainDishMenus: StateFlow<List<Model>>
        get() = _mainDishMenus

    private val _toggle = MutableStateFlow<Boolean>(false)
    val toggle: StateFlow<Boolean>
        get() = _toggle

    fun fetchMainMenus() = viewModelScope.launch {
        getMainMenusUseCase()
            .onSuccess { result ->
                _rowData.value = result
                _mainDishMenus.value = result.map { it.toHomeMenuGridModel() }
            }
            .onFailure {
                println(it)
            }
    }

    fun updateViewMode() {
        if (rowData.value.isNotEmpty()) {
            _mainDishMenus.value =
                if (toggle.value) rowData.value.map {
                    it.toHomeMenuLinearModel()
                } else rowData.value.map {
                    it.toHomeMenuGridModel()
                }
        }
    }

    fun updateToggle(toggle : Boolean) {
        _toggle.value = toggle
    }
}