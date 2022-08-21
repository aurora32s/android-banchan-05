package com.seom.banchan.ui.model.cart

import com.seom.banchan.ui.model.CellType
import com.seom.banchan.ui.model.Model
import com.seom.banchan.util.DELIVERY_FEE
import com.seom.banchan.util.FREE_DELIVERY_LIMIT_PRICE
import com.seom.banchan.util.LIMIT_PRICE

class CartOrderModel (
    override val id: String =  CellType.CART_ORDER_CELL.name,
    override val type: CellType = CellType.CART_ORDER_CELL,
    val totalPrice : Int,
    val limitPrice : Int = LIMIT_PRICE,
    val deliveryFee : Int = DELIVERY_FEE,
    val freeDeliveryLimitPrice : Int = FREE_DELIVERY_LIMIT_PRICE
) : Model(id, type)