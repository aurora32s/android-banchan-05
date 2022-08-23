package com.seom.banchan.ui.adapter.viewholder.menu

import com.seom.banchan.databinding.ItemMenuLargeBinding
import com.seom.banchan.ui.adapter.viewholder.ModelViewHolder
import com.seom.banchan.ui.model.home.HomeMenuLargeModel
import com.seom.banchan.ui.model.home.HomeMenuModel
import com.seom.banchan.util.listener.ModelAdapterListener

class LargeMenuViewHolder(
    private val binding: ItemMenuLargeBinding
) : ModelViewHolder<HomeMenuModel>(binding) {
    override fun bindData(model: HomeMenuModel) {
        binding.menu = model
    }

    override fun bindViews(
        model: HomeMenuModel,
        menuAdapterListener: ModelAdapterListener?
    ) {
        binding.ivMenuThumbnail.setOnClickListener {
            menuAdapterListener?.onClick(it, model, adapterPosition)
        }
        binding.ivCart.setOnClickListener {
            menuAdapterListener?.onClick(it, model, adapterPosition)
        }
    }
}