package com.seom.banchan.ui.adapter.viewholder.home

import android.view.View
import android.widget.AdapterView
import androidx.core.view.get
import com.seom.banchan.databinding.ItemHomeTotalBinding
import com.seom.banchan.ui.adapter.SortSpinnerAdapter
import com.seom.banchan.ui.adapter.viewholder.ModelViewHolder
import com.seom.banchan.ui.model.home.TotalMenuModel

class TotalViewHolder (
    private val binding: ItemHomeTotalBinding
) : ModelViewHolder<TotalMenuModel>(binding) {

    override fun bind(model: TotalMenuModel) {
        binding.total = model
        val adapter = SortSpinnerAdapter(
            context= binding.root.context,
            items = model.sortByItems,
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
                        onSort(model.sortByItems[position].sortCriteria)
                        sortByItems.forEachIndexed { idx, item ->
                            item.isChecked = position == idx
                        }
                    }
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {

                }

            }

    }
}