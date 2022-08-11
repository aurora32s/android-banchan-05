package com.seom.banchan.ui.adapter.viewholder.home

import android.content.Context
import com.seom.banchan.databinding.ItemHomeHeaderBinding
import com.seom.banchan.ui.adapter.viewholder.ModelViewHolder
import com.seom.banchan.ui.model.home.HeaderMenuModel
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.qualifiers.ActivityContext
import javax.inject.Inject

class HeaderViewHolder @Inject constructor(
    private val binding: ItemHomeHeaderBinding
) : ModelViewHolder<HeaderMenuModel>(binding) {

    override fun bind(model: HeaderMenuModel) {
        // TODO 중복 코드 방지 필요
        binding.tvHeader.text = binding.root.context.getString(model.title)
        model.chipTitle?.let {
            binding.tvChip.text = binding.root.context.getString(model.chipTitle)
        }
    }
}