package com.seom.banchan.ui.adapter.viewholder.cart

import android.view.View
import com.seom.banchan.R
import com.seom.banchan.databinding.ItemCartOrderBinding
import com.seom.banchan.ui.adapter.viewholder.ModelViewHolder
import com.seom.banchan.ui.model.Model
import com.seom.banchan.ui.model.cart.CartOrderModel
import com.seom.banchan.util.listener.ModelAdapterListener

class CartOrderViewHolder(
    private val binding: ItemCartOrderBinding
) : ModelViewHolder<CartOrderModel>(binding) {
    override fun bindData(model: CartOrderModel) {
        binding.btOrder.apply {
            if (model.totalPrice < model.limitPrice) {
                this.text = binding.root.context.getString(R.string.cart_minimum_check,model.limitPrice)
                this.isEnabled = false
            } else {
                this.text =
                    if(model.totalPrice >= model.freeDeliveryLimitPrice)
                        binding.root.context.getString(R.string.cart_order_value, model.totalPrice)
                    else binding.root.context.getString(R.string.cart_order_value, model.totalPrice + model.deliveryFee)
                this.isEnabled = true
            }
        }
        binding.tvNotification.apply {
            if (model.totalPrice > model.limitPrice) {
                if (model.totalPrice < model.freeDeliveryLimitPrice) {
                    this.text = binding.root.context.getString(
                        R.string.cart_free_alarm_value,
                        model.freeDeliveryLimitPrice - model.totalPrice
                    )
                    this.visibility = View.VISIBLE
                } else {
                    this.visibility = View.GONE
                }
            } else {
                this.visibility = View.GONE
            }
        }
    }

    override fun bindViews(model: CartOrderModel, menuAdapterListener: ModelAdapterListener?) {
        binding.btOrder.setOnClickListener {
            menuAdapterListener?.onClick(it, model, adapterPosition)
        }
    }
}
