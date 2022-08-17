package com.seom.banchan.ui.adapter

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.seom.banchan.ui.adapter.viewholder.ModelViewHolder
import com.seom.banchan.ui.model.CellType
import com.seom.banchan.ui.model.Model
import com.seom.banchan.util.listener.ModelAdapterListener
import com.seom.banchan.util.mapper.ModelViewHolderMapper

class ModelRecyclerAdapter<M : Model>(
    private val modelAdapterListener: ModelAdapterListener? = null
) : RecyclerView.Adapter<ModelViewHolder<M>>() {

    // recyclerview 에 보여줄 list
    private var modelList: MutableList<Model> = mutableListOf()
    override fun getItemCount() = modelList.size

    override fun getItemViewType(position: Int) = modelList[position].type.ordinal

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ModelViewHolder<M> {
        return ModelViewHolderMapper.map(parent, CellType.values()[viewType])
    }

    @Suppress("UNCHECKED_CAST")
    override fun onBindViewHolder(holder: ModelViewHolder<M>, position: Int) {
        val model = modelList[position] as M
        holder.bindData(model = model)
        holder.bindViews(model = model, menuAdapterListener = modelAdapterListener)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(newList: List<Model>?) {
        newList?.let { modelList = newList.toMutableList() }
        notifyDataSetChanged()
    }

    fun updateList(list:List<Model>){
        modelList = (modelList.subList(0,3) + list).toMutableList()
        notifyItemRangeChanged(modelList.size - list.size , modelList.size - 1)
    }

    fun updateModelAtPosition(model: Model,position: Int){
        modelList[position] = model
        notifyItemChanged(position)
    }
}