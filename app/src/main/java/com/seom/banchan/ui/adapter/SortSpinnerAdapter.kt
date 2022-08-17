package com.seom.banchan.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.seom.banchan.R
import com.seom.banchan.databinding.HeaderSpinnerBinding
import com.seom.banchan.databinding.ItemSpinnerBinding
import com.seom.banchan.ui.model.SortItem

class SortSpinnerAdapter(
    context : Context,
    private val items : List<SortItem>
) : ArrayAdapter<SortItem>(context, R.layout.item_spinner,items) {
    var selectedSortItem = SortItem.BASE

    override fun getCount() = items.size

    override fun getItem(position: Int) = items[position]

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val binding = HeaderSpinnerBinding.inflate(LayoutInflater.from(parent.context),parent, false )
        binding.tvSort.text = context.getString(getItem(position).title)
        return binding.root
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        val binding = ItemSpinnerBinding.inflate(LayoutInflater.from(parent.context),parent, false )
        binding.tvOrder.text = context.getString(getItem(position).title)
        binding.ivCheck.visibility =
            if(selectedSortItem.sortCriteria == getItem(position).sortCriteria) View.VISIBLE else View.GONE
        return binding.root
    }

}