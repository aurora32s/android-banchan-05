package com.seom.banchan.ui.adapter

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.seom.banchan.ui.adapter.viewholder.ModelViewHolder
import com.seom.banchan.ui.model.CellType
import com.seom.banchan.ui.model.Model
import com.seom.banchan.util.listener.ModelAdapterListener
import com.seom.banchan.util.mapper.ModelViewHolderMapper

class PagingAdapter<M : Model>(
    private val modelAdapterListener: ModelAdapterListener? = null,
    diffUtilItemCallback: DiffItemCallback<M> = DiffItemCallback()
) : PagingDataAdapter<M, ModelViewHolder<M>>(
    diffUtilItemCallback
) {

    override fun getItemViewType(position: Int) = (getItem(position) as M).type.ordinal

    override fun onBindViewHolder(holder: ModelViewHolder<M>, position: Int) {
        val model = getItem(position) as M
        holder.bindData(model)
        holder.bindViews(model, modelAdapterListener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ModelViewHolder<M> {
        return ModelViewHolderMapper.map(parent, CellType.values()[viewType], null)
    }
}