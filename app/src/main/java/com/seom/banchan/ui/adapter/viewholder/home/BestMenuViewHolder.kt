package com.seom.banchan.ui.adapter.viewholder.home

import android.util.Log
import android.view.MotionEvent
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.seom.banchan.databinding.ItemBestMenuBinding
import com.seom.banchan.ui.adapter.ItemDecoration.BestItemDecoration
import com.seom.banchan.ui.adapter.ModelRecyclerAdapter
import com.seom.banchan.ui.adapter.viewholder.ModelViewHolder
import com.seom.banchan.ui.model.home.CategoryMenuModel
import com.seom.banchan.ui.model.home.HomeMenuModel
import com.seom.banchan.util.ext.addHorizontalAndVerticalScrollListener
import com.seom.banchan.util.listener.ModelAdapterListener

class BestMenuViewHolder(
    private val binding: ItemBestMenuBinding,
    menuAdapterListener: ModelAdapterListener?
) : ModelViewHolder<CategoryMenuModel>(binding) {

    val bestMenuAdapter =
        ModelRecyclerAdapter<HomeMenuModel>(modelAdapterListener = menuAdapterListener)

    override fun bindData(model: CategoryMenuModel) {
        binding.tvCategoryName.text = model.categoryName
        binding.rvBest.layoutManager =
            LinearLayoutManager(binding.root.context, LinearLayoutManager.HORIZONTAL, false)
        binding.rvBest.adapter = bestMenuAdapter
        binding.rvBest.addHorizontalAndVerticalScrollListener()
        if (binding.rvBest.itemDecorationCount != 0)
            binding.rvBest.removeItemDecorationAt(0)

        binding.rvBest.addItemDecoration(BestItemDecoration(binding.root.context))
        binding.rvBest.itemAnimator = null
        bestMenuAdapter.submitList(model.menus)
    }

    override fun bindDataWithPayLoads(model : CategoryMenuModel,payload: MutableList<Any>) {
        val changedMenu = payload.first() as HomeMenuModel
        model.menus.forEachIndexed { index, menu ->
            if((menu as HomeMenuModel).id == changedMenu.id){
                bestMenuAdapter.updateModelAtPosition(menu, index)
            }
        }
    }
}