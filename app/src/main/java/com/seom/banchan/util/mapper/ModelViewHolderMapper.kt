package com.seom.banchan.util.mapper

import android.view.LayoutInflater
import android.view.ViewGroup
import com.seom.banchan.databinding.ItemBestMenuBinding
import com.seom.banchan.databinding.ItemMenuSmallBinding
import com.seom.banchan.ui.adapter.viewholder.ModelViewHolder
import com.seom.banchan.ui.adapter.viewholder.home.BestMenuViewHolder
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
            CellType.HEADER_CELL -> BestMenuViewHolder(
                ItemBestMenuBinding.inflate(inflater, parent, false)
            )
            CellType.MENU_LIST_CELL -> BestMenuViewHolder(
                ItemBestMenuBinding.inflate(inflater, parent, false)
            )
            CellType.MENU_CELL -> SmallMenuViewHolder(
                ItemMenuSmallBinding.inflate(inflater, parent, false)
            )
        }

        return viewHolder as ModelViewHolder<M>
    }

}