package com.seom.banchan.ui.adapter.viewholder.order

import com.seom.banchan.R
import com.seom.banchan.databinding.ItemOrderBinding
import com.seom.banchan.ui.adapter.viewholder.ModelViewHolder
import com.seom.banchan.ui.model.order.OrderDeliveryState
import com.seom.banchan.ui.model.order.OrderListItemUiModel
import com.seom.banchan.util.listener.ModelAdapterListener

class OrderListItemViewHolder(
    private val binding: ItemOrderBinding
) : ModelViewHolder<OrderListItemUiModel>(binding) {

    override fun bindData(model: OrderListItemUiModel) {
        binding.order = model
        binding.tvOrderState.setTextColor(
            binding.root.context.getColor(
                when (model.deliveryCompleted) {
                    OrderDeliveryState.DELIVERING -> R.color.black
                    OrderDeliveryState.DELIVERED -> R.color.primaryAccent
                }
            )
        )
    }

    override fun bindViews(
        model: OrderListItemUiModel,
        menuAdapterListener: ModelAdapterListener?
    ) {
        binding.btnSelectItem.setOnClickListener {
            menuAdapterListener?.onClick(it, model, adapterPosition)
        }
    }

}