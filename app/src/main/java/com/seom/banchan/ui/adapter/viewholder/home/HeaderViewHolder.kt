package com.seom.banchan.ui.adapter.viewholder.home

import com.seom.banchan.databinding.ItemHomeHeaderBinding
import com.seom.banchan.ui.adapter.viewholder.ModelViewHolder
import com.seom.banchan.ui.model.home.HeaderMenuModel

class HeaderViewHolder(
    private val binding: ItemHomeHeaderBinding
) : ModelViewHolder<HeaderMenuModel>(binding) {

    override fun bind(model: HeaderMenuModel) {
        binding.tvHeader.text = model.title
    }
}