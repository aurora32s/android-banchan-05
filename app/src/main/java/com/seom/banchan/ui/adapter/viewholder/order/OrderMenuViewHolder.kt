package com.seom.banchan.ui.adapter.viewholder.order

import com.seom.banchan.databinding.ItemOrderDetailMenuBinding
import com.seom.banchan.ui.adapter.viewholder.ModelViewHolder
import com.seom.banchan.ui.model.order.OrderMenuUiModel

/**
 * 주문한 메뉴 view holder
 */
class OrderMenuViewHolder(
    private val binding: ItemOrderDetailMenuBinding
) : ModelViewHolder<OrderMenuUiModel>(binding) {
    override fun bindData(model: OrderMenuUiModel) {
        binding.menu = model
    }
}