package com.seom.banchan.ui.model.order

import com.seom.banchan.ui.model.CellType
import com.seom.banchan.ui.model.Model
import com.seom.banchan.util.DELIVERY_FEE

class OrderInfoModel (
    override val id: String = "order_info",
    override val type: CellType = CellType.ORDER_INFO_CELL,
    val orderPrice : Int,
    val deliveryFee : Int = DELIVERY_FEE
) : Model(id, type)