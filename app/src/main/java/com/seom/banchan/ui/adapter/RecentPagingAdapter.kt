package com.seom.banchan.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.seom.banchan.databinding.ItemMenuRecentBinding
import com.seom.banchan.ui.model.Model
import com.seom.banchan.ui.model.home.HomeMenuModel
import com.seom.banchan.util.listener.ModelAdapterListener

class RecentPagingAdapter(
    private val modelAdapterListener: ModelAdapterListener? = null
) : PagingDataAdapter<HomeMenuModel, RecentPagingAdapter.RecentViewHolder>(
    RecentDiffCallback()
) {

    override fun onBindViewHolder(holder: RecentViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
            holder.bindViews(it as Model, modelAdapterListener)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecentViewHolder {
        return RecentViewHolder(
            ItemMenuRecentBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        )
    }

    class RecentViewHolder(private val binding: ItemMenuRecentBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(homeMenuModel: HomeMenuModel?) {
            homeMenuModel?.let {
                binding.menu = it
            }
            binding.root.layoutParams = ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.MATCH_PARENT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT
            )
        }

        fun bindViews(homeMenuModel: Model?,modelAdapterListener : ModelAdapterListener? ){
            homeMenuModel?.let { model ->
                binding.ivCart.setOnClickListener {
                    modelAdapterListener?.onClick(it, model, position = bindingAdapterPosition )
                }
                binding.ivMenuThumbnail.setOnClickListener {
                    modelAdapterListener?.onClick(it, model, position = bindingAdapterPosition )
                }
            }
        }
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