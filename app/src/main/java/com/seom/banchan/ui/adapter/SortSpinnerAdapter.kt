package com.seom.banchan.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.seom.banchan.R
import com.seom.banchan.data.api.SortCriteria
import com.seom.banchan.databinding.ItemSpinnerBinding
import com.seom.banchan.databinding.LayoutSpinnerBinding
import com.seom.banchan.ui.model.Sort

class SortSpinnerAdapter(
    context : Context,
    private val items : List<Sort>
) : ArrayAdapter<Sort>(context, R.layout.item_spinner,items) {

    override fun getCount() = items.size

    override fun getItem(position: Int) = items[position]

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val binding = LayoutSpinnerBinding.inflate(LayoutInflater.from(parent.context),parent, false )
        binding.tvOrder.text = context.getString(getItem(position).name)
        return binding.root
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        val binding = ItemSpinnerBinding.inflate(LayoutInflater.from(parent.context),parent, false )
        binding.tvOrder.text = context.getString(getItem(position).name)
        binding.ivCheck.visibility =
            if(getItem(position).isChecked) View.VISIBLE else View.GONE
        return binding.root
    }

}