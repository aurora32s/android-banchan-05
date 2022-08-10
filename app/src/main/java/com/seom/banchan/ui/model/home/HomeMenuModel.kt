package com.seom.banchan.ui.model.home

import com.seom.banchan.domain.model.MenuModel

data class HomeMenuModel(
    val menu: MenuModel,
    val discountRate: Int, // 소수점 버림
    val count: Int = 0, // 선택 개수
    val isLoadedCart: Boolean = false // 장바구니 포함 여부
)
