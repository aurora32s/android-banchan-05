package com.seom.banchan.ui.model.detail

import com.seom.banchan.ui.model.CellType
import com.seom.banchan.ui.model.Model

data class DetailMenuModel(
    override val id: String = "menuDetailInfo",
    override val type: CellType = CellType.MENU_DETAIL_INFO_CELL,
    val detailMenu: MenuDetailModel,
    val discountRate: Int
) : Model(id, type)
