package com.seom.banchan.ui.home.sidedish

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seom.banchan.R
import com.seom.banchan.domain.model.*
import com.seom.banchan.domain.usecase.GetSideMenusUseCase
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
class SideDishViewModel @Inject constructor(
    private val getSideMenusUseCase: GetSideMenusUseCase
) : ViewModel() {
    private val _rowData = MutableStateFlow<List<MenuModel>>(emptyList())
    private val rowData: StateFlow<List<MenuModel>>
        get() = _rowData

    private val _sideDishMenus = MutableStateFlow<List<Model>>(emptyList())
    val sideDishMenus: StateFlow<List<Model>>
        get() = _sideDishMenus

    private val baseMenu = listOf<Model>(
        HeaderMenuModel(id = "header", title = R.string.header_side),
        FilterMenuModel(id = "filter")
    )

    private val _toggle = (baseMenu[1] as FilterMenuModel).toggle // 개선 필요
    val toggle: StateFlow<Boolean>
        get() = _toggle

    fun fetchSideMenus() = viewModelScope.launch {
        getSideMenusUseCase()
            .onSuccess { result ->
                _rowData.value = result
                _sideDishMenus.value = baseMenu + result.map { it.toHomeMenuGridModel() }
            }
            .onFailure {
                println(it)
            }
    }

    fun updateViewMode() {
        if (rowData.value.isNotEmpty()) {
            _sideDishMenus.value =
                baseMenu + if (toggle.value) rowData.value.map {
                    it.toHomeMenuLinearModel()
                } else rowData.value.map {
                    it.toHomeMenuGridModel()
                }
        }
    }
}