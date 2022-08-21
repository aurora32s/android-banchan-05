package com.seom.banchan.ui.adapter.viewholder.order

import com.seom.banchan.databinding.ItemOrderStateBinding
import com.seom.banchan.ui.adapter.viewholder.ModelViewHolder
import com.seom.banchan.ui.model.order.OrderStateUiModel

/**
 * 배달 상태를 나타내는 viewHolder
 */
class OrderDeliveryStateViewHolder(
    private val binding: ItemOrderStateBinding
) : ModelViewHolder<OrderStateUiModel>(binding) {

    override fun bindData(model: OrderStateUiModel) {
        binding.deliveryType = model.orderDeliveryState
    }
}