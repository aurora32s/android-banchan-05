package com.seom.banchan.ui.model.order

import com.seom.banchan.ui.model.CellType
import com.seom.banchan.ui.model.Model
import com.seom.banchan.util.DEFAULT_DELIVERY_FEE

class OrderInfoModel (
    override val id: String = CellType.ORDER_INFO_CELL.name,
    override val type: CellType = CellType.ORDER_INFO_CELL,
    val orderPrice : Int = 0,
    val deliveryFee : Int = DEFAULT_DELIVERY_FEE
) : Model(id, type)