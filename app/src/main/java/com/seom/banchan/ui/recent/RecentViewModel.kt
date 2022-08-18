package com.seom.banchan.ui.recent

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seom.banchan.domain.model.home.toHomeMenuModel
import com.seom.banchan.domain.usecase.GetRecentMenusUseCase
import com.seom.banchan.ui.model.CellType
import com.seom.banchan.ui.model.home.HomeMenuModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecentViewModel @Inject constructor(
    private val getRecentMenusUseCase: GetRecentMenusUseCase
) : ViewModel() {

    private val _recentMenus = MutableStateFlow<List<HomeMenuModel>>(emptyList())
    val recentMenus : StateFlow<List<HomeMenuModel>>
        get() = _recentMenus

    fun getRecentMenus(){
        viewModelScope.launch {
            getRecentMenusUseCase().collectLatest {
                _recentMenus.value = it.map {
                    it.toHomeMenuModel(cellType = CellType.MENU_RECENT_CELL)
                }
            }
        }
    }
}