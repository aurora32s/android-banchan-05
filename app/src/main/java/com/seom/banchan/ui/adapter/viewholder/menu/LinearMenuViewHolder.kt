package com.seom.banchan.ui.adapter.viewholder.menu

import com.seom.banchan.databinding.ItemMenuLinearBinding
import com.seom.banchan.ui.adapter.viewholder.ModelViewHolder
import com.seom.banchan.ui.model.home.HomeMenuLinearModel

class LinearMenuViewHolder(
    private val binding: ItemMenuLinearBinding
) : ModelViewHolder<HomeMenuLinearModel>(binding) {
    override fun bind(model: HomeMenuLinearModel) {
        binding.menu = model
    }
}