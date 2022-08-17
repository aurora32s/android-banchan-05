package com.seom.banchan.ui.adapter.viewholder.home

import com.seom.banchan.databinding.ItemHomeFilterBinding
import com.seom.banchan.ui.adapter.viewholder.ModelViewHolder
import com.seom.banchan.ui.model.home.FilterMenuModel

class FilterViewHolder (
    private val binding: ItemHomeFilterBinding
) : ModelViewHolder<FilterMenuModel>(binding) {

    override fun bindData(model: FilterMenuModel) {
        binding.filter = model
        binding.rgFilter.setOnCheckedChangeListener { _, _ ->
            model.onToggle(
                binding.rbLinear.isChecked
            )
        }
    }
}