package com.seom.banchan.ui.adapter.viewholder.order

import com.seom.banchan.databinding.ItemOrderBinding
import com.seom.banchan.ui.adapter.viewholder.ModelViewHolder
import com.seom.banchan.ui.model.order.OrderListItemModel
import com.seom.banchan.util.listener.ModelAdapterListener

class OrderListItemViewHolder(
    private val binding: ItemOrderBinding
) : ModelViewHolder<OrderListItemModel>(binding) {

    override fun bindData(model: OrderListItemModel) {
        binding.order = model
    }

    override fun bindViews(model: OrderListItemModel, menuAdapterListener: ModelAdapterListener?) {
        super.bindViews(model, menuAdapterListener)
    }

}