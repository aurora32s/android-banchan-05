package com.seom.banchan.ui.adapter.viewholder.home

import com.seom.banchan.databinding.ItemHomeTotalBinding
import com.seom.banchan.ui.adapter.SortSpinnerAdapter
import com.seom.banchan.ui.adapter.viewholder.ModelViewHolder
import com.seom.banchan.ui.model.home.TotalMenuModel

class TotalViewHolder (
    private val binding: ItemHomeTotalBinding
) : ModelViewHolder<TotalMenuModel>(binding) {

    override fun bind(model: TotalMenuModel) {
        binding.total = model
        binding.spSort.adapter = SortSpinnerAdapter(binding.root.context,model.sortByItems )
    }
}