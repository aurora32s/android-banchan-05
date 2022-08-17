package com.seom.banchan.ui.model.order

import com.seom.banchan.ui.model.CellType
import com.seom.banchan.ui.model.Model

class OrderInfoModel (
    override val id: String,
    override val type: CellType = CellType.ORDER_INFO_CELL,
) : Model(id, type)