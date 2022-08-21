package com.seom.banchan.ui.adapter.viewholder.cart

import android.util.Log
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.seom.banchan.databinding.ItemCartRecentBinding
import com.seom.banchan.ui.adapter.ItemDecoration.BestItemDecoration
import com.seom.banchan.ui.adapter.ModelRecyclerAdapter
import com.seom.banchan.ui.adapter.viewholder.ModelViewHolder
import com.seom.banchan.ui.model.Model
import com.seom.banchan.ui.model.cart.CartOrderModel
import com.seom.banchan.ui.model.cart.CartRecentModel
import com.seom.banchan.ui.model.home.HomeMenuModel
import com.seom.banchan.util.listener.ModelAdapterListener

class CartRecentViewHolder(
    private val binding: ItemCartRecentBinding
) : ModelViewHolder<CartRecentModel>(binding) {
    override fun bindData(model: CartRecentModel) {

        val recentMenuAdapter = ModelRecyclerAdapter<HomeMenuModel>()
        recentMenuAdapter.submitList(model.recentMenus)

        binding.rvCartRecent.run {
            layoutManager = LinearLayoutManager(binding.root.context, LinearLayoutManager.HORIZONTAL, false)
            adapter = recentMenuAdapter
            if(itemDecorationCount > 0) removeItemDecorationAt(0)
            isVisible = model.recentMenus.isNotEmpty()
        }

        binding.tvEmpty.isVisible = model.recentMenus.isEmpty()

    }
    override fun bindViews(model: CartRecentModel, menuAdapterListener: ModelAdapterListener?) {
        binding.tvAll.setOnClickListener {
            menuAdapterListener?.onClick(it, model, adapterPosition)
        }
    }
}
