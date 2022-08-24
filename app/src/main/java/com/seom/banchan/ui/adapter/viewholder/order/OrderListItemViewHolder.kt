package com.seom.banchan.ui.adapter.viewholder.order

import com.seom.banchan.R
import com.seom.banchan.databinding.ItemOrderBinding
import com.seom.banchan.ui.adapter.viewholder.ModelViewHolder
import com.seom.banchan.ui.model.order.OrderDeliveryState
import com.seom.banchan.ui.model.order.OrderListItemUiModel
import com.seom.banchan.util.listener.ModelAdapterListener
import com.seom.banchan.util.provider.ResourceProvider

class OrderListItemViewHolder(
    private val binding: ItemOrderBinding,
    private val resourceProvider: ResourceProvider?
) : ModelViewHolder<OrderListItemUiModel>(binding) {

    override fun bindData(model: OrderListItemUiModel) {
        binding.order = model
        binding.tvOrderState.setTextColor(
            resourceProvider?.getColor(
                when (model.deliveryCompleted) {
                    OrderDeliveryState.DELIVERING -> R.color.black
                    OrderDeliveryState.DELIVERED -> R.color.primaryAccent
                }
            ) ?: binding.root.context.getColor(R.color.black)
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