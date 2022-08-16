package com.seom.banchan.ui.adapter.viewholder.home

import android.view.View
import androidx.core.view.isVisible
import com.seom.banchan.databinding.ItemHomeHeaderBinding
import com.seom.banchan.ui.adapter.viewholder.ModelViewHolder
import com.seom.banchan.ui.model.home.HeaderMenuModel
import javax.inject.Inject

class HeaderViewHolder (
    private val binding: ItemHomeHeaderBinding
) : ModelViewHolder<HeaderMenuModel>(binding) {

    override fun bindData(model: HeaderMenuModel) {
        // TODO 중복 코드 방지 필요
        binding.tvHeader.text = binding.root.context.getString(model.title)
        binding.tvChip.isVisible = model.chipTitle != null
        model.chipTitle?.let {
            binding.tvChip.text = binding.root.context.getString(it)
        }
    }
}