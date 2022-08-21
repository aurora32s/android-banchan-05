package com.seom.banchan.ui.adapter.viewholder.cart

import com.seom.banchan.R
import com.seom.banchan.databinding.ItemCartCheckBinding
import com.seom.banchan.ui.adapter.viewholder.ModelViewHolder
import com.seom.banchan.ui.model.Model
import com.seom.banchan.ui.model.cart.CartCheckModel
import com.seom.banchan.util.listener.ModelAdapterListener

class CartCheckViewHolder(
    private val binding: ItemCartCheckBinding
) : ModelViewHolder<CartCheckModel>(binding) {
    override fun bindData(model: CartCheckModel) {
        binding.cbAll.isChecked = model.atLeastChecked
        binding.tvAll.text =
            if (model.atLeastChecked)
                binding.root.context.getString(R.string.cart_uncheck)
            else
                binding.root.context.getString(R.string.cart_check_all)
    }

    override fun bindViews(model: CartCheckModel, menuAdapterListener: ModelAdapterListener?) {
        binding.tvAll.setOnClickListener {
            menuAdapterListener?.onClick(it, model, adapterPosition)
        }

        binding.cbAll.setOnClickListener {
            menuAdapterListener?.onClick(it, model, adapterPosition)
        }

        binding.tvDelete.setOnClickListener {
            menuAdapterListener?.onClick(it, model, adapterPosition)
        }

    }
}
