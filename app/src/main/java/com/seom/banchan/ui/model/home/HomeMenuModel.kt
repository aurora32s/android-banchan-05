package com.seom.banchan.ui.model.home

import com.seom.banchan.domain.model.MenuModel
import com.seom.banchan.ui.model.CellType
import com.seom.banchan.ui.model.Model

data class HomeMenuModel(
    override val id: String,
    override val type: CellType = CellType.MENU_CELL,
    val menu: MenuModel,
    val discountRate: Int, // 소수점 버림
    val count: Int = 0, // 선택 개수
    val isLoadedCart: Boolean = false // 장바구니 포함 여부
) : Model(id, type)
