package com.seom.banchan.ui.main

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {
    private val _mainUiState = MutableStateFlow<MainUiState>(MainUiState.UnInitialized)
    val mainUiState = _mainUiState.asStateFlow()

    fun setLoadSuccess() {
        _mainUiState.value = MainUiState.SuccessLoad
    }
}

sealed interface MainUiState {
    object UnInitialized : MainUiState
    object SuccessLoad : MainUiState
}