package com.seom.banchan.ui.adapter.viewholder.menu

import com.seom.banchan.databinding.ItemMenuGridBinding
import com.seom.banchan.databinding.ItemMenuSmallBinding
import com.seom.banchan.ui.adapter.viewholder.ModelViewHolder
import com.seom.banchan.ui.model.home.HomeMenuGridModel
import com.seom.banchan.ui.model.home.HomeMenuModel

class GridMenuViewHolder(
    private val binding: ItemMenuGridBinding
) : ModelViewHolder<HomeMenuGridModel>(binding) {
    override fun bind(model: HomeMenuGridModel) {
        binding.menu = model
    }
}