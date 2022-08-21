package com.seom.banchan.ui.adapter.viewholder.order

import com.seom.banchan.databinding.ItemOrderStateBinding
import com.seom.banchan.ui.adapter.viewholder.ModelViewHolder
import com.seom.banchan.ui.model.order.OrderStateUiModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

/**
 * 배달 상태를 나타내는 viewHolder
 */
class OrderDeliveryStateViewHolder(
    private val binding: ItemOrderStateBinding
) : ModelViewHolder<OrderStateUiModel>(binding) {

    override fun bindData(model: OrderStateUiModel) {
        CoroutineScope(Dispatchers.Main).launch {
            model.orderDeliveryState.collect {
                binding.tvOrderState.text = binding.root.context.getString(it.stateLongTitle)
            }
        }
    }
}