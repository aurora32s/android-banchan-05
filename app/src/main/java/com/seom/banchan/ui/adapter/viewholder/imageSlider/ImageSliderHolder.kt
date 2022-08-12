package com.seom.banchan.ui.adapter.viewholder.imageSlider

import com.seom.banchan.databinding.ItemImageSliderBinding
import com.seom.banchan.ui.adapter.viewholder.ModelViewHolder
import com.seom.banchan.ui.model.imageSlider.ImageSliderModel

class ImageSliderHolder(
    private val binding: ItemImageSliderBinding
): ModelViewHolder<ImageSliderModel>(binding) {
    override fun bind(model: ImageSliderModel) {
        binding.image = model
    }
}