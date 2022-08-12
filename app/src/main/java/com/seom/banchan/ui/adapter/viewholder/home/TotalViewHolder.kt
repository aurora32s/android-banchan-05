package com.seom.banchan.ui.adapter.viewholder.home

import android.util.Log
import com.seom.banchan.databinding.ItemHomeFilterBinding
import com.seom.banchan.databinding.ItemHomeTotalBinding
import com.seom.banchan.ui.adapter.viewholder.ModelViewHolder
import com.seom.banchan.ui.model.home.FilterMenuModel
import com.seom.banchan.ui.model.home.TotalMenuModel
import javax.inject.Inject

class TotalViewHolder (
    private val binding: ItemHomeTotalBinding
) : ModelViewHolder<TotalMenuModel>(binding) {

    override fun bind(model: TotalMenuModel) {
        binding.total = model
    }
}