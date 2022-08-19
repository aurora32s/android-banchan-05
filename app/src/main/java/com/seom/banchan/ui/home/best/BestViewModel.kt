package com.seom.banchan.ui.home.best

import androidx.lifecycle.*
import com.seom.banchan.R
import com.seom.banchan.data.db.dao.CartDao
import com.seom.banchan.data.db.entity.CartMenuEntity
import com.seom.banchan.domain.model.home.CategoryModel
import com.seom.banchan.domain.model.home.toUiModel
import com.seom.banchan.domain.usecase.GetCartMenusIdUseCase
import com.seom.banchan.domain.usecase.GetMenuWithCategoriesUseCase
import com.seom.banchan.ui.model.Model
import com.seom.banchan.ui.model.home.HeaderMenuModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BestViewModel @Inject constructor(
    private val getMenuWithCategoriesUseCase: GetMenuWithCategoriesUseCase,
    private val getCartMenusIdUseCase: GetCartMenusIdUseCase,
) : ViewModel() {
    val cartMenusId = getCartMenusIdUseCase()
    private val _bestMenus = MutableStateFlow<List<CategoryModel>>(emptyList())
    val bestMenus = _bestMenus.asStateFlow().combine(cartMenusId) { menus, carts ->
        baseMenu + menus.map { it.toUiModel(isBest = true, cartMenus = carts) }
    }

    private val baseMenu = listOf<Model>(
        HeaderMenuModel(id = "header", title = R.string.header_best, chipTitle = R.string.tab_best)
    )

    fun fetchBestMenus() = viewModelScope.launch {
        getMenuWithCategoriesUseCase()
            .onSuccess { result ->
                _bestMenus.value = result
            }
            .onFailure {
                println(it)
            }
    }
}