package com.seom.banchan.util.mapper

import android.view.LayoutInflater
import android.view.ViewGroup
<<<<<<< HEAD
import com.seom.banchan.databinding.*
=======
import com.seom.banchan.databinding.ItemBestMenuBinding
import com.seom.banchan.databinding.ItemHomeHeaderBinding
import com.seom.banchan.databinding.ItemImageSliderBinding
import com.seom.banchan.databinding.ItemMenuSmallBinding
>>>>>>> 1ef98ca ([FEAT] #8 - 상세 화면 상단 이미지 슬라이드 및 indicator UI 구성 완료)
import com.seom.banchan.ui.adapter.viewholder.ModelViewHolder
import com.seom.banchan.ui.adapter.viewholder.home.BestMenuViewHolder
import com.seom.banchan.ui.adapter.viewholder.home.FilterViewHolder
import com.seom.banchan.ui.adapter.viewholder.home.HeaderViewHolder
<<<<<<< HEAD
import com.seom.banchan.ui.adapter.viewholder.home.TotalViewHolder
import com.seom.banchan.ui.adapter.viewholder.menu.GridMenuViewHolder
import com.seom.banchan.ui.adapter.viewholder.menu.LinearMenuViewHolder
=======
import com.seom.banchan.ui.adapter.viewholder.imageSlider.ImageSliderHolder
>>>>>>> 1ef98ca ([FEAT] #8 - 상세 화면 상단 이미지 슬라이드 및 indicator UI 구성 완료)
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
<<<<<<< HEAD
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
=======
            CellType.IMAGE_SLIDER -> ImageSliderHolder(
                ItemImageSliderBinding.inflate(inflater, parent, false)
>>>>>>> 1ef98ca ([FEAT] #8 - 상세 화면 상단 이미지 슬라이드 및 indicator UI 구성 완료)
            )
        }
        return viewHolder as ModelViewHolder<M>
    }

}