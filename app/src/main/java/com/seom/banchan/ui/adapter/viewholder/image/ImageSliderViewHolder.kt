package com.seom.banchan.ui.adapter.viewholder.image

import com.seom.banchan.databinding.ItemImageSliderBinding
import com.seom.banchan.ui.adapter.viewholder.ModelViewHolder
import com.seom.banchan.ui.model.imageSlider.ImageSliderModel

class ImageSliderViewHolder(
    private val binding: ItemImageSliderBinding
): ModelViewHolder<ImageSliderModel>(binding) {
    override fun bind(model: ImageSliderModel) {
        binding.image = model
    }
}