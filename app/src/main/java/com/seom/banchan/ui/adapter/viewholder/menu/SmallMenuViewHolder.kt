package com.seom.banchan.ui.adapter.viewholder.menu

import androidx.constraintlayout.widget.ConstraintLayout
import com.seom.banchan.databinding.ItemMenuSmallBinding
import com.seom.banchan.ui.adapter.viewholder.ModelViewHolder
import com.seom.banchan.ui.model.CellType
import com.seom.banchan.ui.model.home.HomeMenuModel
import com.seom.banchan.util.listener.ModelAdapterListener

class SmallMenuViewHolder(
    private val binding: ItemMenuSmallBinding
) : ModelViewHolder<HomeMenuModel>(binding) {
    override fun bindData(model: HomeMenuModel) {
        binding.menu = model
        binding.recent = model.type == CellType.MENU_RECENT_CELL

        // 기획전이 아닌 곳에서 사용되는 smallMenu의 경우 가로를 match_parent로 하고
        // margin 을 주는 것이 맞는 것 같아 아래를 추가하였습니다.
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
        binding.ivMenuThumbnail.setOnClickListener {
            menuAdapterListener?.onClick(it, model, adapterPosition)
        }
        binding.ivCart.setOnClickListener {
            menuAdapterListener?.onClick(it, model, adapterPosition)
        }
    }
}