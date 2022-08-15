package com.seom.banchan.util.mapper

import android.view.LayoutInflater
import android.view.ViewGroup
import com.seom.banchan.databinding.*
import com.seom.banchan.databinding.ItemBestMenuBinding
import com.seom.banchan.databinding.ItemHomeHeaderBinding
import com.seom.banchan.databinding.ItemImageSliderBinding
import com.seom.banchan.databinding.ItemMenuSmallBinding
import com.seom.banchan.ui.adapter.viewholder.ModelViewHolder
import com.seom.banchan.ui.adapter.viewholder.detail.MenuInfoViewHolder
import com.seom.banchan.ui.adapter.viewholder.home.BestMenuViewHolder
import com.seom.banchan.ui.adapter.viewholder.home.FilterViewHolder
import com.seom.banchan.ui.adapter.viewholder.home.HeaderViewHolder
import com.seom.banchan.ui.adapter.viewholder.home.TotalViewHolder
import com.seom.banchan.ui.adapter.viewholder.menu.GridMenuViewHolder
import com.seom.banchan.ui.adapter.viewholder.menu.LinearMenuViewHolder
import com.seom.banchan.ui.adapter.viewholder.image.ImageListItemViewHolder
import com.seom.banchan.ui.adapter.viewholder.image.ImageSliderViewHolder
import com.seom.banchan.ui.adapter.viewholder.menu.SmallMenuViewHolder
import com.seom.banchan.ui.model.CellType
import com.seom.banchan.ui.model.Model

object ModelViewHolderMapper {

    @Suppress("UNCHECKED_CAST")
    fun <M : Model> map(
        parent: ViewGroup,
        type: CellType
    ): ModelViewHolder<M> {
        val inflater = LayoutInflater.from(parent.context)
        val viewHolder = when (type) {
            CellType.HEADER_CELL -> HeaderViewHolder(
                ItemHomeHeaderBinding.inflate(inflater, parent, false)
            )
            CellType.MENU_LIST_CELL -> BestMenuViewHolder(
                ItemBestMenuBinding.inflate(inflater, parent, false)
            )
            CellType.MENU_CELL -> SmallMenuViewHolder(
                ItemMenuSmallBinding.inflate(inflater, parent, false)
            )
            CellType.FILTER_CELL -> FilterViewHolder(
                ItemHomeFilterBinding.inflate(inflater, parent, false)
            )
            CellType.TOTAL_CELL -> TotalViewHolder(
                ItemHomeTotalBinding.inflate(inflater, parent, false)
            )
            CellType.MENU_LINEAR_CELL -> LinearMenuViewHolder(
                ItemMenuLinearBinding.inflate(inflater, parent, false)
            )
            CellType.MENU_GRID_CELL -> GridMenuViewHolder(
                ItemMenuGridBinding.inflate(inflater, parent, false)
            )
            CellType.IMAGE_SLIDER_CELL -> ImageSliderViewHolder(
                ItemImageSliderBinding.inflate(inflater, parent, false)
            )
            CellType.IMAGE_LIST_CELL -> ImageListItemViewHolder(
                ItemImageListBinding.inflate(inflater, parent, false)
            )
            CellType.MENU_DETAIL_INFO_CELL -> MenuInfoViewHolder(
                ItemMenuInfoBinding.inflate(inflater, parent, false)
            )
        }
        return viewHolder as ModelViewHolder<M>
    }

}