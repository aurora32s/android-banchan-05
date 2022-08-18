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
        binding.cbMenu.setOnClickListener {
            model.onCheck(model)
        }
    }
}
