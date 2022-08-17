package com.seom.banchan.ui.adapter.viewholder.home

import android.view.View
import android.widget.AdapterView
import com.seom.banchan.databinding.ItemHomeFilterBinding
import com.seom.banchan.ui.adapter.SortSpinnerAdapter
import com.seom.banchan.ui.adapter.viewholder.ModelViewHolder
import com.seom.banchan.ui.home.maindish.ToggleState
import com.seom.banchan.ui.model.home.FilterMenuModel

class FilterViewHolder (
    private val binding: ItemHomeFilterBinding
) : ModelViewHolder<FilterMenuModel>(binding) {

    override fun bindData(model: FilterMenuModel) {
        binding.filter = model
        binding.rbGrid.setOnClickListener {
            model.onToggle(
                ToggleState.GRID
            )
        }
        binding.rbLinear.setOnClickListener {
            model.onToggle(
                ToggleState.LINEAR
            )
        }
//        binding.rgFilter.setOnCheckedChangeListener { _, _ ->
//            model.onToggle(
//                binding.rbLinear.isChecked
//            )
//        }
    }
}