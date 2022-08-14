package com.seom.banchan.ui.adapter.viewholder.menu

import com.seom.banchan.databinding.ItemMenuLargeBinding
import com.seom.banchan.ui.adapter.viewholder.ModelViewHolder
import com.seom.banchan.ui.model.home.HomeMenuLargeModel

class LargeMenuViewHolder(
    private val binding: ItemMenuLargeBinding
) : ModelViewHolder<HomeMenuLargeModel>(binding) {
    override fun bind(model: HomeMenuLargeModel) {
        binding.menu = model
    }
}