package com.seom.banchan.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seom.banchan.domain.model.detail.toUiModel
import com.seom.banchan.domain.model.home.MenuModel
import com.seom.banchan.domain.usecase.GetMenuDetailUseCase
import com.seom.banchan.ui.model.detail.DetailMenuUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getMenuDetailUseCase: GetMenuDetailUseCase
) : ViewModel() {

    private val _detailMenuUiModel = MutableStateFlow<DetailUiState>(DetailUiState.UnInitialized)
    val detailMenuModel: StateFlow<DetailUiState>
        get() = _detailMenuUiModel

    fun fetchData(menu: MenuModel?) = viewModelScope.launch {
        if (menu == null) {
            _detailMenuUiModel.value = DetailUiState.Error
            return@launch
        }
        getMenuDetailUseCase(menu.id)
            .onSuccess { _detailMenuUiModel.value = DetailUiState.Success(it.toUiModel()) }
    }

}

sealed interface DetailUiState {
    object UnInitialized : DetailUiState
    data class Success(
        val detailMenu: DetailMenuUiModel
    ) : DetailUiState

    object Error : DetailUiState
}