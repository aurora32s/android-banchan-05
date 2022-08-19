package com.seom.banchan.ui.adapter.viewholder.cart

import com.seom.banchan.databinding.ItemCartMenuBinding
import com.seom.banchan.ui.adapter.viewholder.ModelViewHolder
import com.seom.banchan.ui.model.Model
import com.seom.banchan.ui.model.cart.CartMenuModel

class CartMenuViewHolder(
    private val binding: ItemCartMenuBinding
) : ModelViewHolder<CartMenuModel>(binding) {
    override fun bindData(model: CartMenuModel) {
        binding.model = model
        binding.etCount.setText( model.count.toString())
        binding.cbMenu.setOnClickListener {
            model.onCheck(model)
        }
        binding.ivDelete.setOnClickListener {
            model.onRemove(model)
        }
        binding.ivUp.setOnClickListener {
            model.onIncrease(model)
        }
        binding.ivDown.setOnClickListener {
            model.onDecrease(model)
        }
    }
}
