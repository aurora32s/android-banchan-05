package com.seom.banchan.util.mapper

import android.view.LayoutInflater
import android.view.ViewGroup
import com.seom.banchan.databinding.*
import com.seom.banchan.ui.adapter.viewholder.ModelViewHolder
import com.seom.banchan.ui.adapter.viewholder.detail.DetailBottomButtonViewHolder
import com.seom.banchan.ui.adapter.viewholder.detail.MenuCountViewHolder
import com.seom.banchan.ui.adapter.viewholder.detail.MenuInfoViewHolder
import com.seom.banchan.ui.adapter.viewholder.home.*
import com.seom.banchan.ui.adapter.viewholder.image.ImageListItemViewHolder
import com.seom.banchan.ui.adapter.viewholder.image.ImageSliderViewHolder
import com.seom.banchan.ui.adapter.viewholder.menu.LargeMenuViewHolder
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
            CellType.MENU_LARGE_CELL -> LargeMenuViewHolder(
                ItemMenuLargeBinding.inflate(inflater, parent, false)
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
            CellType.DETAIL_COUNT_CELL -> MenuCountViewHolder(
                ItemDetailCountBinding.inflate(inflater, parent, false)
            )
            CellType.DETAIL_BOTTOM_BUTTON_CELL -> DetailBottomButtonViewHolder(
                ItemDetailBottomButtonBinding.inflate(inflater, parent, false)
            )
            CellType.SORT_CELL -> SortViewHolder(
                ItemHomeSortBinding.inflate(inflater, parent, false)
            )
        }
        return viewHolder as ModelViewHolder<M>
    }

}