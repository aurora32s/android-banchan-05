package com.seom.banchan.ui.adapter.viewholder.recent

import androidx.constraintlayout.widget.ConstraintLayout
import com.seom.banchan.databinding.ItemMenuRecentBinding
import com.seom.banchan.ui.adapter.viewholder.ModelViewHolder
import com.seom.banchan.ui.model.CellType
import com.seom.banchan.ui.model.home.HomeMenuModel
import com.seom.banchan.util.listener.ModelAdapterListener

class RecentMenuViewHolder(
    private val binding: ItemMenuRecentBinding
) : ModelViewHolder<HomeMenuModel>(binding) {
    override fun bindData(model: HomeMenuModel) {
        binding.menu = model
        binding.inCart = model.type == CellType.CART_MENU_RECENT_CELL

        if(model.type != CellType.CART_MENU_RECENT_CELL)
            binding.root.layoutParams = ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.MATCH_PARENT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT
            )
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