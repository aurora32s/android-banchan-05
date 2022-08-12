package com.seom.banchan.ui.adapter.viewholder.detail

import com.seom.banchan.databinding.ItemImageSliderBinding
import com.seom.banchan.databinding.ItemMenuInfoBinding
import com.seom.banchan.ui.adapter.viewholder.ModelViewHolder
import com.seom.banchan.ui.model.detail.DetailMenuModel

class DetailMenuViewHolder(
    private val binding: ItemMenuInfoBinding
) : ModelViewHolder<DetailMenuModel>(binding) {
    override fun bind(model: DetailMenuModel) {
        binding.detail = model
    }
}