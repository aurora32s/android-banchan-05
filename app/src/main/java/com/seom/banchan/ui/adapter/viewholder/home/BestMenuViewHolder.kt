package com.seom.banchan.ui.adapter.viewholder.home

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.seom.banchan.databinding.ItemBestMenuBinding
import com.seom.banchan.ui.adapter.ModelRecyclerAdapter
import com.seom.banchan.ui.adapter.viewholder.ModelViewHolder
import com.seom.banchan.ui.model.home.CategoryMenuModel
import com.seom.banchan.ui.model.home.HomeMenuModel

class BestMenuViewHolder(
    private val binding: ItemBestMenuBinding
) : ModelViewHolder<CategoryMenuModel>(binding) {

    override fun bind(model: CategoryMenuModel) {
        binding.tvCategoryName.text = model.categoryName

        val bestMenuAdapter = ModelRecyclerAdapter<HomeMenuModel>()
        binding.rvBest.layoutManager =
            LinearLayoutManager(binding.root.context, LinearLayoutManager.HORIZONTAL, false)
        binding.rvBest.adapter = bestMenuAdapter
        bestMenuAdapter.submitList(model.menus)
    }
}