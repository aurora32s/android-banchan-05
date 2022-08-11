package com.seom.banchan.ui.home.best

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seom.banchan.domain.model.toUiModel
import com.seom.banchan.domain.usecase.GetMenuWithCategoriesUseCase
import com.seom.banchan.ui.model.home.CategoryMenuModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BestViewModel @Inject constructor(
    private val getMenuWithCategoriesUseCase: GetMenuWithCategoriesUseCase
) : ViewModel() {
    private val _bestMenus = MutableLiveData<List<CategoryMenuModel>>(emptyList())
    val bestMenus: LiveData<List<CategoryMenuModel>>
        get() = _bestMenus

    fun fetchBestMenus() = viewModelScope.launch {
        getMenuWithCategoriesUseCase()
            .onSuccess { result ->
                _bestMenus.value = result.map { it.toUiModel() }
            }
            .onFailure {
                println(it)
            }
    }
}