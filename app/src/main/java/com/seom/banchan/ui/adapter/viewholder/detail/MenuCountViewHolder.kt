package com.seom.banchan.ui.adapter.viewholder.detail

import com.seom.banchan.databinding.ItemDetailCountBinding
import com.seom.banchan.ui.adapter.viewholder.ModelViewHolder
import com.seom.banchan.ui.model.detail.MenuCountModel

class MenuCountViewHolder(
    private val binding: ItemDetailCountBinding
) : ModelViewHolder<MenuCountModel>(binding) {
    override fun bindData(model: MenuCountModel) {
        binding.count = model.count
    }
}