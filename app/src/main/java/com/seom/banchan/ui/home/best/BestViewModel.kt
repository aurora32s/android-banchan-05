package com.seom.banchan.ui.home.best

import androidx.lifecycle.*
import com.seom.banchan.R
import com.seom.banchan.domain.model.home.CategoryModel
import com.seom.banchan.domain.model.home.MenuModel
import com.seom.banchan.domain.model.home.toUiModel
import com.seom.banchan.domain.usecase.GetCartMenusUseCase
import com.seom.banchan.domain.usecase.GetMenuWithCategoriesUseCase
import com.seom.banchan.ui.model.Model
import com.seom.banchan.ui.model.home.HeaderMenuModel
import com.seom.banchan.ui.model.home.HomeMenuModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BestViewModel @Inject constructor(
    private val getMenuWithCategoriesUseCase: GetMenuWithCategoriesUseCase,
    getCartMenusUseCase: GetCartMenusUseCase
) : ViewModel() {

    private val _bestUiState = MutableStateFlow<BestUiState>(BestUiState.UnInitialized)
    val bestUiState = _bestUiState.asStateFlow()

    private val cartMenus = getCartMenusUseCase()
    private val _bestMenus = MutableStateFlow<List<CategoryModel>>(emptyList())
    val bestMenus = _bestMenus.asStateFlow()
        .combine(cartMenus) { menus, carts ->
            menus.map { it.toUiModel(cartMenus = carts) }
        }

    val baseMenu = listOf<Model>(
        HeaderMenuModel(id = "header", title = R.string.header_best, chipTitle = R.string.tab_best)
    )

    var selectedCart : HomeMenuModel? = null
        private set

    fun fetchBestMenus() = viewModelScope.launch {
        getMenuWithCategoriesUseCase()
            .onSuccess { result ->
                _bestMenus.value = result
                _bestUiState.value = BestUiState.SuccessFetchMenus
            }
            .onFailure {
                _bestUiState.value = BestUiState.FailFetchMenus
            }
    }

    fun updateSelectedCart(homeMenuModel : HomeMenuModel) {
        selectedCart = homeMenuModel
    }
}

sealed interface BestUiState {
    object UnInitialized : BestUiState
    object SuccessFetchMenus : BestUiState
    object FailFetchMenus : BestUiState
}