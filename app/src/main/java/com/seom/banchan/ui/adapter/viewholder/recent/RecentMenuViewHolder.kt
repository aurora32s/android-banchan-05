package com.seom.banchan.ui.adapter.viewholder.recent

import androidx.constraintlayout.widget.ConstraintLayout
import com.seom.banchan.databinding.ItemMenuRecentBinding
import com.seom.banchan.ui.adapter.viewholder.ModelViewHolder
import com.seom.banchan.ui.model.recent.RecentMenuModel
import com.seom.banchan.util.listener.ModelAdapterListener

class RecentMenuViewHolder(
    private val binding: ItemMenuRecentBinding
) : ModelViewHolder<RecentMenuModel>(binding) {
    override fun bindData(model: RecentMenuModel) {
        binding.menu = model

        if(!model.inCart)
            binding.root.layoutParams = ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.MATCH_PARENT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT
            )
    }

    override fun bindViews(
        model: RecentMenuModel,
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