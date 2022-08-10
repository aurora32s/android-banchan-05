package com.seom.banchan.ui.model.home

sealed class UiModel {
    data class Header(
        val id : Int,
        val title : String,
        val chipList : List<String> = emptyList()
    ) : UiModel()
    data class CategoryTitle(
        val title : String
    ) : UiModel()
    data class ListItem(
        val homeMenuModels: List<HomeMenuModel>
    ) : UiModel()
}
