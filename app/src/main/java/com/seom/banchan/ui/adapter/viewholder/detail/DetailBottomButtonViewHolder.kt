package com.seom.banchan.ui.adapter.viewholder.detail

import com.seom.banchan.databinding.ItemDetailBottomButtonBinding
import com.seom.banchan.ui.adapter.viewholder.ModelViewHolder
import com.seom.banchan.ui.model.detail.DetailBottomButtonModel
import com.seom.banchan.util.listener.ModelAdapterListener
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailBottomButtonViewHolder(
    private val binding: ItemDetailBottomButtonBinding
) : ModelViewHolder<DetailBottomButtonModel>(binding) {
    override fun bindData(model: DetailBottomButtonModel) {
        CoroutineScope(Dispatchers.IO).launch {
            model.totalCount.collect {
                binding.totalPrice = it
            }
        }
    }

    override fun bindViews(
        model: DetailBottomButtonModel,
        menuAdapterListener: ModelAdapterListener?
    ) {
        binding.btnAddCart.setOnClickListener {
            menuAdapterListener?.onClick(it, model, adapterPosition)
        }
    }
}