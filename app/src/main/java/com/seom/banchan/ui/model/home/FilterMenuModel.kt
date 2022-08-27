package com.seom.banchan.ui.model.home

import com.seom.banchan.ui.home.maindish.ToggleState
import com.seom.banchan.ui.model.CellType
import com.seom.banchan.ui.model.Model
import com.seom.banchan.ui.model.SortItem

data class FilterMenuModel(
    override val id: String,
    override val type: CellType = CellType.FILTER_CELL,
    val toggleState: ToggleState,
    val onToggle : (ToggleState) -> Unit,
) : Model(id, type)
