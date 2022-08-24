package com.seom.banchan.ui.model

/**
 * 데이터가 없는 경우에 보여줄 공통 UI Model
 */
data class NoneDataUiModel(
    override val id: String = "none",
    override val type: CellType = CellType.NONE_DATE_CELL
) : Model(id, type)
