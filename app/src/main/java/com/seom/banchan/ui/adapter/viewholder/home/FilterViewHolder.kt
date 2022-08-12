package com.seom.banchan.ui.adapter.viewholder.home

import android.util.Log
import com.seom.banchan.databinding.ItemHomeFilterBinding
import com.seom.banchan.ui.adapter.viewholder.ModelViewHolder
import com.seom.banchan.ui.model.home.FilterMenuModel
import javax.inject.Inject

class FilterViewHolder (
    private val binding: ItemHomeFilterBinding
) : ModelViewHolder<FilterMenuModel>(binding) {

    override fun bind(model: FilterMenuModel) {
        binding.filter = model
    }
}