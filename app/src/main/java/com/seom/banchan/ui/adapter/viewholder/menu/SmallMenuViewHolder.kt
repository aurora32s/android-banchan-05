package com.seom.banchan.ui.adapter.viewholder.menu

import com.seom.banchan.databinding.ItemMenuSmallBinding
import com.seom.banchan.ui.adapter.viewholder.ModelViewHolder
import com.seom.banchan.ui.model.home.HomeMenuModel

class SmallMenuViewHolder(
    private val binding: ItemMenuSmallBinding
) : ModelViewHolder<HomeMenuModel>(binding) {
    override fun bind(model: HomeMenuModel) {
        binding.menu = model
    }
}