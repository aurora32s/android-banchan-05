package com.seom.banchan.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.seom.banchan.ui.adapter.viewholder.ModelViewHolder
import com.seom.banchan.ui.model.CellType
import com.seom.banchan.ui.model.Model
import com.seom.banchan.util.mapper.ModelViewHolderMapper

class ModelRecyclerAdapter<M : Model> : RecyclerView.Adapter<ModelViewHolder<M>>() {

    // recyclerview 에 보여줄 list
    private var modelList: List<Model> = emptyList()
    override fun getItemCount() = modelList.size

    override fun getItemViewType(position: Int) = modelList[position].type.ordinal

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ModelViewHolder<M> {
        return ModelViewHolderMapper.map(parent, CellType.values()[viewType])
    }

    @Suppress("UNCHECKED_CAST")
    override fun onBindViewHolder(holder: ModelViewHolder<M>, position: Int) {
        holder.bind(modelList[position] as M)
    }

    fun submitList(newList: List<Model>?) {
        newList?.let { modelList = newList }
    }
}