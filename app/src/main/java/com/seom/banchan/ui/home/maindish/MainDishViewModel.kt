package com.seom.banchan.ui.home.maindish

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seom.banchan.R
import com.seom.banchan.domain.model.toUiModel
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
    private val getMenuWithCategoriesUseCase: GetMenuWithCategoriesUseCase
) : ViewModel() {
    private val _bestMenus = MutableStateFlow<List<Model>>(emptyList())
    val bestMenus: StateFlow<List<Model>>
        get() = _bestMenus

    private val baseMenu = listOf<Model>(
        HeaderMenuModel(id = "header", title = R.string.header_main),
        FilterMenuModel(id = "filter")
    )

    fun fetchBestMenus() = viewModelScope.launch {
        getMenuWithCategoriesUseCase()
            .onSuccess { result ->
                _bestMenus.value = baseMenu + result.map { it.toUiModel() }
            }
            .onFailure {
                println(it)
            }
    }
}