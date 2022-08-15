package com.seom.banchan.ui.adapter.viewholder.menu

import androidx.constraintlayout.widget.ConstraintLayout
import com.seom.banchan.databinding.ItemMenuSmallBinding
import com.seom.banchan.ui.adapter.viewholder.ModelViewHolder
import com.seom.banchan.ui.model.home.HomeMenuModel
import com.seom.banchan.util.listener.ModelAdapterListener

class SmallMenuViewHolder(
    private val binding: ItemMenuSmallBinding
) : ModelViewHolder<HomeMenuModel>(binding) {
    override fun bindData(model: HomeMenuModel) {
        binding.menu = model
        if(!model.isBest)
            binding.root.layoutParams = ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.MATCH_PARENT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT
            )
    }

    override fun bindViews(
        model: HomeMenuModel,
        menuAdapterListener: ModelAdapterListener?
    ) {
        binding.root.setOnClickListener {
            menuAdapterListener?.onClick(it, model, adapterPosition)
        }
    }
}