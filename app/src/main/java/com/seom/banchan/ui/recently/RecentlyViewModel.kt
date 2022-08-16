package com.seom.banchan.ui.recently

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seom.banchan.domain.model.toHomeMenuModel
import com.seom.banchan.domain.usecase.GetRecentlyMenusUseCase
import com.seom.banchan.ui.model.CellType
import com.seom.banchan.ui.model.home.HomeMenuModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecentlyViewModel @Inject constructor(
    private val getRecentlyMenusUseCase: GetRecentlyMenusUseCase
) : ViewModel() {

    private val _recentlyMenus = MutableStateFlow<List<HomeMenuModel>>(emptyList())
    val recentlyMenus : StateFlow<List<HomeMenuModel>>
        get() = _recentlyMenus

    fun getRecentlyMenus(){
        viewModelScope.launch {
            getRecentlyMenusUseCase().collectLatest {
                _recentlyMenus.value = it.map {
                    it.toHomeMenuModel(cellType = CellType.MENU_RECENTLY_CELL)
                }
            }

        }
    }
}
