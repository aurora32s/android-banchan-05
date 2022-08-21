package com.seom.banchan.ui.adapter.viewholder.cart

import com.seom.banchan.databinding.ItemCartMenuBinding
import com.seom.banchan.ui.adapter.viewholder.ModelViewHolder
import com.seom.banchan.ui.model.Model
import com.seom.banchan.ui.model.cart.CartMenuModel
import com.seom.banchan.util.listener.ModelAdapterListener

class CartMenuViewHolder(
    private val binding: ItemCartMenuBinding
) : ModelViewHolder<CartMenuModel>(binding) {
    override fun bindData(model: CartMenuModel) {
        binding.model = model
        binding.etCount.setText(model.count.toString())
    }

    override fun bindViews(model: CartMenuModel, menuAdapterListener: ModelAdapterListener?) {
        binding.cbMenu.setOnClickListener {
            menuAdapterListener?.onClick(it, model, adapterPosition)
        }
        binding.ivDelete.setOnClickListener {
            menuAdapterListener?.onClick(it, model, adapterPosition)
        }
        binding.ivUp.setOnClickListener {
            menuAdapterListener?.onClick(it, model, adapterPosition)
        }
        binding.ivDown.setOnClickListener {
            menuAdapterListener?.onClick(it, model, adapterPosition)
        }
    }
}
