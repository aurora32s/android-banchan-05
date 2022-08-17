package com.seom.banchan.ui.adapter.viewholder.detail

import com.seom.banchan.databinding.ItemDetailCountBinding
import com.seom.banchan.ui.adapter.viewholder.ModelViewHolder
import com.seom.banchan.ui.model.detail.MenuCountModel
import com.seom.banchan.util.listener.ModelAdapterListener
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MenuCountViewHolder(
    private val binding: ItemDetailCountBinding
) : ModelViewHolder<MenuCountModel>(binding) {
    override fun bindData(model: MenuCountModel) {
        CoroutineScope(Dispatchers.Main).launch {
            model.count.collect { binding.count = it }
        }
    }

    override fun bindViews(
        model: MenuCountModel,
        menuAdapterListener: ModelAdapterListener?
    ) {
        binding.ivUpCount.setOnClickListener {
            menuAdapterListener?.onClick(it, model, adapterPosition)
        }
        binding.ivDownCount.setOnClickListener {
            menuAdapterListener?.onClick(it, model, adapterPosition)
        }
    }
}