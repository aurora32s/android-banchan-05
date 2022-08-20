package com.seom.banchan.ui.adapter.viewholder.menu

import com.seom.banchan.databinding.ItemMenuLargeBinding
import com.seom.banchan.ui.adapter.viewholder.ModelViewHolder
import com.seom.banchan.ui.model.home.HomeMenuLargeModel
import com.seom.banchan.ui.model.home.HomeMenuModel

class LargeMenuViewHolder(
    private val binding: ItemMenuLargeBinding
) : ModelViewHolder<HomeMenuModel>(binding) {
    override fun bindData(model: HomeMenuModel) {
        binding.menu = model
    }
}