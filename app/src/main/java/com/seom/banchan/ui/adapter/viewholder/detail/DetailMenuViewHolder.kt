package com.seom.banchan.ui.adapter.viewholder.detail

import com.seom.banchan.databinding.ItemMenuDetailBinding
import com.seom.banchan.ui.adapter.viewholder.ModelViewHolder
import com.seom.banchan.ui.model.detail.MenuDetailModel


class DetailMenuViewHolder(
    private val binding: ItemMenuDetailBinding
): ModelViewHolder<MenuDetailModel>(binding) {
    override fun bind(model: MenuDetailModel) {
        binding.menu = model
    }
}
