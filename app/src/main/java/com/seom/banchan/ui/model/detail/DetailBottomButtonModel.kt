package com.seom.banchan.ui.model.detail

import com.seom.banchan.ui.model.CellType
import com.seom.banchan.ui.model.Model
import kotlinx.coroutines.flow.Flow

data class DetailBottomButtonModel(
    override val id: String,
    override val type: CellType = CellType.DETAIL_BOTTOM_BUTTON_CELL,
    val totalCount: Flow<Int>
):Model(id, type)
