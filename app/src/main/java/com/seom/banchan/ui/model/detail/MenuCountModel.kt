package com.seom.banchan.ui.model.detail

import androidx.lifecycle.LiveData
import com.seom.banchan.ui.model.CellType
import com.seom.banchan.ui.model.Model
import kotlinx.coroutines.flow.StateFlow

data class MenuCountModel(
    override val id: String,
    override val type: CellType = CellType.DETAIL_COUNT_CELL,
    val count: StateFlow<Int>
) : Model(id, type)
