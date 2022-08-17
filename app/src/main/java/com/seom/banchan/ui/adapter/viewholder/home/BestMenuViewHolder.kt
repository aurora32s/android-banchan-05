package com.seom.banchan.ui.adapter.viewholder.home

import android.view.MotionEvent
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.seom.banchan.databinding.ItemBestMenuBinding
import com.seom.banchan.ui.adapter.ModelRecyclerAdapter
import com.seom.banchan.ui.adapter.viewholder.ModelViewHolder
import com.seom.banchan.ui.model.home.CategoryMenuModel
import com.seom.banchan.ui.model.home.HomeMenuModel
import com.seom.banchan.util.listener.ModelAdapterListener

class BestMenuViewHolder(
    private val binding: ItemBestMenuBinding
) : ModelViewHolder<CategoryMenuModel>(binding) {

    override fun bindData(model: CategoryMenuModel) {
        binding.tvCategoryName.text = model.categoryName
    }

    override fun bindViews(
        model: CategoryMenuModel,
        menuAdapterListener: ModelAdapterListener?
    ) {
        val bestMenuAdapter = ModelRecyclerAdapter<HomeMenuModel>(menuAdapterListener)
        binding.rvBest.layoutManager =
            LinearLayoutManager(binding.root.context, LinearLayoutManager.HORIZONTAL, false)
        binding.rvBest.adapter = bestMenuAdapter
        bestMenuAdapter.submitList(model.menus)
    }
}