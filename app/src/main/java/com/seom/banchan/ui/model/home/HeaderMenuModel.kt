package com.seom.banchan.ui.model.home

import androidx.annotation.StringRes
import com.seom.banchan.ui.model.CellType
import com.seom.banchan.ui.model.Model

data class HeaderMenuModel(
    override val id: String,
    override val type: CellType = CellType.HEADER_CELL,
    @StringRes
    val title: Int,
    @StringRes
    val chipTitle: Int? = null
) : Model(id, type)
