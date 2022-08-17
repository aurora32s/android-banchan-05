package com.seom.banchan.ui.adapter.viewholder.home

import android.view.View
import android.widget.AdapterView
import com.seom.banchan.databinding.ItemHomeSortBinding
import com.seom.banchan.databinding.ItemHomeTotalBinding
import com.seom.banchan.ui.adapter.SortSpinnerAdapter
import com.seom.banchan.ui.adapter.viewholder.ModelViewHolder
import com.seom.banchan.ui.model.home.SortMenuModel
import com.seom.banchan.ui.model.home.TotalMenuModel

class SortViewHolder (
    private val binding: ItemHomeSortBinding
) : ModelViewHolder<SortMenuModel>(binding) {

    override fun bind(model: SortMenuModel) {
        val adapter = SortSpinnerAdapter(
            context= binding.root.context,
            items = model.sortItems,
        )
        binding.spSort.adapter = adapter
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