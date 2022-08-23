package com.seom.banchan.ui.adapter.viewholder.cart

import androidx.core.view.isVisible
import com.seom.banchan.R
import com.seom.banchan.databinding.ItemCartOrderBinding
import com.seom.banchan.ui.adapter.viewholder.ModelViewHolder
import com.seom.banchan.ui.model.cart.CartOrderModel
import com.seom.banchan.util.DEFAULT_DELIVERY_FEE
import com.seom.banchan.util.FREE_DELIVERY_MINIMUM_PRICE
import com.seom.banchan.util.MINIMUM_PRICE
import com.seom.banchan.util.listener.ModelAdapterListener

class CartOrderViewHolder(
    private val binding: ItemCartOrderBinding
) : ModelViewHolder<CartOrderModel>(binding) {
    override fun bindData(model: CartOrderModel) {
        val context = binding.root.context
        val isAbleOrder = model.totalPrice >= MINIMUM_PRICE
        val isFreeDelivery = model.totalPrice >= FREE_DELIVERY_MINIMUM_PRICE

        binding.btOrder.isEnabled = isAbleOrder
        binding.btOrder.text = when {
            isAbleOrder -> context.getString(
                R.string.cart_order_value,
                model.totalPrice + if (!isFreeDelivery) DEFAULT_DELIVERY_FEE else 0
            )
            else -> context.getString(R.string.cart_minimum_check,MINIMUM_PRICE)
        }

        binding.tvNotification.isVisible = isAbleOrder && isFreeDelivery.not()
        binding.tvNotification.text = context.getString(
            R.string.cart_free_alarm_value,
            FREE_DELIVERY_MINIMUM_PRICE - model.totalPrice
        )
    }

    override fun bindViews(model: CartOrderModel, menuAdapterListener: ModelAdapterListener?) {
        binding.btOrder.setOnClickListener {
            menuAdapterListener?.onClick(it, model, adapterPosition)
        }
    }
}
