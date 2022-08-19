package com.seom.banchan.ui.adapter.viewholder.cart

import com.seom.banchan.R
import com.seom.banchan.databinding.ItemCartCheckBinding
import com.seom.banchan.ui.adapter.viewholder.ModelViewHolder
import com.seom.banchan.ui.model.Model
import com.seom.banchan.ui.model.cart.CartCheckModel

class CartCheckViewHolder(
    private val binding: ItemCartCheckBinding
) : ModelViewHolder<CartCheckModel>(binding) {
    override fun bindData(model: CartCheckModel) {
        binding.cbAll.isChecked = model.atLeastChecked
        binding.tvAll.text =
            if(model.atLeastChecked)
                binding.root.context.getString(R.string.cart_uncheck)
            else
                binding.root.context.getString(R.string.cart_check_all)

        binding.tvAll.setOnClickListener {
            model.onAllCheck()
        }

        binding.cbAll.setOnClickListener {
            model.onAllCheck()
        }

        binding.tvDelete.setOnClickListener {
            model.onRemove()
        }
    }
}
