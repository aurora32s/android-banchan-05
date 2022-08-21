package com.seom.banchan.ui.adapter.viewholder.cart

import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.seom.banchan.databinding.ItemCartRecentBinding
import com.seom.banchan.ui.adapter.ItemDecoration.BestItemDecoration
import com.seom.banchan.ui.adapter.ModelRecyclerAdapter
import com.seom.banchan.ui.adapter.viewholder.ModelViewHolder
import com.seom.banchan.ui.model.Model
import com.seom.banchan.ui.model.cart.CartRecentModel
import com.seom.banchan.ui.model.home.HomeMenuModel

class CartRecentViewHolder(
    private val binding: ItemCartRecentBinding
) : ModelViewHolder<CartRecentModel>(binding) {
    override fun bindData(model: CartRecentModel) {

        val recentMenuAdapter = ModelRecyclerAdapter<HomeMenuModel>()

        binding.rvCartRecent.layoutManager =
            LinearLayoutManager(binding.root.context, LinearLayoutManager.HORIZONTAL, false)
        binding.rvCartRecent.adapter = recentMenuAdapter
        if(binding.rvCartRecent.itemDecorationCount > 0) binding.rvCartRecent.removeItemDecorationAt(0)
        binding.rvCartRecent.addItemDecoration(BestItemDecoration(binding.root.context))
        recentMenuAdapter.submitList(model.recentMenus)

        binding.tvAll.setOnClickListener {

        }
    }
}
