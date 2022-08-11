package com.seom.banchan.ui.model.home

import com.seom.banchan.ui.model.CellType
import com.seom.banchan.ui.model.Model
import kotlinx.coroutines.flow.MutableStateFlow

data class FilterMenuModel(
    override val id: String,
    override val type: CellType = CellType.FILTER_CELL,
    var toggle : MutableStateFlow<Boolean> = MutableStateFlow(false)
) : Model(id, type)
