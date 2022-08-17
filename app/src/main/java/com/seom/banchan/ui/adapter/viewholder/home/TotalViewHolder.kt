package com.seom.banchan.ui.adapter.viewholder.home

import android.view.View
import android.widget.AdapterView
import com.seom.banchan.databinding.ItemHomeTotalBinding
import com.seom.banchan.ui.adapter.SortSpinnerAdapter
import com.seom.banchan.ui.adapter.viewholder.ModelViewHolder
import com.seom.banchan.ui.model.home.TotalMenuModel

class TotalViewHolder (
    private val binding: ItemHomeTotalBinding
) : ModelViewHolder<TotalMenuModel>(binding) {

    override fun bindData(model: TotalMenuModel) {
        binding.total = model
    }
}