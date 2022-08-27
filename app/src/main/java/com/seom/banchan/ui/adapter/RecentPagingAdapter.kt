package com.seom.banchan.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.seom.banchan.databinding.ItemMenuRecentBinding
import com.seom.banchan.ui.adapter.viewholder.recent.RecentMenuViewHolder
import com.seom.banchan.ui.model.home.HomeMenuModel
import com.seom.banchan.util.listener.ModelAdapterListener

class RecentPagingAdapter(
    private val modelAdapterListener: ModelAdapterListener? = null
) : PagingDataAdapter<HomeMenuModel, RecentMenuViewHolder>(
    RecentDiffCallback()
) {

    override fun onBindViewHolder(holder: RecentMenuViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bindData(it)
            holder.bindViews(it, modelAdapterListener)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecentMenuViewHolder {
        return RecentMenuViewHolder(
            ItemMenuRecentBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        )
    }

    private class RecentDiffCallback : DiffUtil.ItemCallback<HomeMenuModel>(){
        override fun areItemsTheSame(oldItem: HomeMenuModel, newItem: HomeMenuModel): Boolean {
            return oldItem.menu.id == newItem.menu.id
        }

        override fun areContentsTheSame(oldItem: HomeMenuModel, newItem: HomeMenuModel): Boolean {
            return oldItem == newItem
        }
    }
}