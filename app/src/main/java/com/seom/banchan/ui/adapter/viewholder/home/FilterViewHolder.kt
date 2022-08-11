package com.seom.banchan.ui.adapter.viewholder.home

import android.content.Context
import com.seom.banchan.databinding.ItemHomeFilterBinding
import com.seom.banchan.databinding.ItemHomeHeaderBinding
import com.seom.banchan.ui.adapter.viewholder.ModelViewHolder
import com.seom.banchan.ui.model.home.FilterMenuModel
import com.seom.banchan.ui.model.home.HeaderMenuModel
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.qualifiers.ActivityContext
import javax.inject.Inject

class FilterViewHolder @Inject constructor(
    private val binding: ItemHomeFilterBinding
) : ModelViewHolder<FilterMenuModel>(binding) {

    override fun bind(model: FilterMenuModel) {

    }
}