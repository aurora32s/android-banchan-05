package com.seom.banchan.ui.adapter.viewholder.home

import android.view.View
import android.widget.AdapterView
import com.seom.banchan.databinding.ItemHomeSortBinding
import com.seom.banchan.ui.adapter.SortSpinnerAdapter
import com.seom.banchan.ui.adapter.viewholder.ModelViewHolder
import com.seom.banchan.ui.model.home.SortMenuModel

class SortViewHolder(
    private val binding: ItemHomeSortBinding,
) : ModelViewHolder<SortMenuModel>(binding) {

    val adapter = SortSpinnerAdapter(
        context = binding.root.context
    )

    init {
        binding.spSort.adapter = adapter
    }

    override fun bindData(model: SortMenuModel) {
        adapter.selectedSortItem = model.sortState
        binding.spSort.setSelection(adapter.items.indexOf(model.sortState))
        binding.spSort.onItemSelectedListener =
        object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                adapterView: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                model.run {
                    onSort(adapter.getItem(position))
                    adapter.selectedSortItem = adapter.getItem(position)
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }
    }
}