package com.seom.banchan.ui.adapter.viewholder.order

import com.seom.banchan.R
import com.seom.banchan.databinding.ItemCartOrderBinding
import com.seom.banchan.databinding.ItemOrderInfoBinding
import com.seom.banchan.ui.adapter.viewholder.ModelViewHolder
import com.seom.banchan.ui.model.Model
import com.seom.banchan.ui.model.order.OrderInfoModel
import com.seom.banchan.util.DELIVERY_FEE
import com.seom.banchan.util.FREE_DELIVERY_LIMIT_PRICE

class OrderInfoViewHolder(
    private val binding: ItemOrderInfoBinding
) : ModelViewHolder<OrderInfoModel>(binding) {
    override fun bindData(model: OrderInfoModel) {
        binding.model = model

        binding.tvDeliveryFee.text =
            if(model.orderPrice >= FREE_DELIVERY_LIMIT_PRICE)
                binding.root.context.getString(R.string.menu_price_value,0)
            else binding.root.context.getString(R.string.menu_price_value,DELIVERY_FEE)

        binding.tvOrderTotalPrice.text =
            if(model.orderPrice >= FREE_DELIVERY_LIMIT_PRICE)
                binding.root.context.getString(R.string.menu_price_value,model.orderPrice)
            else binding.root.context.getString(R.string.menu_price_value,model.orderPrice + DELIVERY_FEE)
    }
}
