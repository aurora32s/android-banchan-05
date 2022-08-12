package com.seom.banchan.ui.adapter.viewholder.home

import android.view.View
import com.seom.banchan.databinding.ItemHomeHeaderBinding
import com.seom.banchan.ui.adapter.viewholder.ModelViewHolder
import com.seom.banchan.ui.model.home.HeaderMenuModel
import javax.inject.Inject

class HeaderViewHolder (
    private val binding: ItemHomeHeaderBinding
) : ModelViewHolder<HeaderMenuModel>(binding) {

    override fun bind(model: HeaderMenuModel) {
        // TODO 중복 코드 방지 필요
        binding.tvHeader.text = binding.root.context.getString(model.title)
        if(model.chipTitle == null){
            binding.tvChip.visibility = View.GONE
        }else {
            binding.tvChip.visibility = View.VISIBLE
            binding.tvChip.text = binding.root.context.getString(model.chipTitle)
        }
    }
}