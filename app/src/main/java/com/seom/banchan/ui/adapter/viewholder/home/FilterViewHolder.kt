package com.seom.banchan.ui.adapter.viewholder.home

import android.view.View
import android.widget.AdapterView
import com.seom.banchan.databinding.ItemHomeFilterBinding
import com.seom.banchan.ui.adapter.SortSpinnerAdapter
import com.seom.banchan.ui.adapter.viewholder.ModelViewHolder
import com.seom.banchan.ui.model.home.FilterMenuModel

class FilterViewHolder (
    private val binding: ItemHomeFilterBinding
) : ModelViewHolder<FilterMenuModel>(binding) {

    override fun bindData(model: FilterMenuModel) {
        binding.filter = model
        binding.rbLinear.isChecked = model.togglePosition == 1
        binding.rbGrid.isChecked = model.togglePosition == 0
        binding.rgFilter.setOnCheckedChangeListener { _, _ ->
            model.onToggle(
                binding.rbLinear.isChecked
            )
        }
        val adapter = SortSpinnerAdapter(
            context= binding.root.context,
            items = model.sortByItems,
        )
        binding.spSort.adapter = adapter
        binding.spSort.setSelection(model.position)
        binding.spSort.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    adapterView: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    model.also {
                        if(it.position != position) {
                            it.onSort(position)
                            it.sortByItems.forEachIndexed { idx, item ->
                                item.isChecked = position == idx
                            }
                        }
                    }
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {

                }
            }
    }
}