package com.seom.banchan.ui.model.cart

import com.seom.banchan.domain.model.home.MenuModel
import com.seom.banchan.domain.model.order.OrderItemModel
import com.seom.banchan.ui.model.CellType
import com.seom.banchan.ui.model.Model
import com.seom.banchan.ui.model.ModelId
import com.seom.banchan.ui.model.home.HomeMenuModel

data class CartMenuUiModel(
    override val id: String = CellType.CART_MENU_CELL.name,
    override val type: CellType = CellType.CART_MENU_CELL,
    val menu: MenuModel,
    var count: Int = 1,
    var checked: Boolean = true,
) : Model(id, type)

/**
 * 주문하기 위해 주문 아이템을 위한 domain model 로 전환
 */
fun CartMenuUiModel.toOrderItemModel() = OrderItemModel(
    menuId = menu.id,
    name = menu.name,
    image = menu.image,
    salePrice = menu.salePrice,
    count = count
)