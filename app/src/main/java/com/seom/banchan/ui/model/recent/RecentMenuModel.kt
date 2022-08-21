package com.seom.banchan.ui.model.recent

import com.seom.banchan.domain.model.home.MenuModel
import com.seom.banchan.ui.model.CellType
import com.seom.banchan.ui.model.Model

data class RecentMenuModel(
    override val id: String,
    override val type: CellType = CellType.MENU_RECENT_CELL,
    val menu: MenuModel,
    val inCart : Boolean = false,
    val count: Int = 1, // 선택 개수
    val isLoadedCart: Boolean = false // 장바구니 포함 여부
) : Model(id, type)
