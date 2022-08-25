package com.seom.banchan.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.seom.banchan.databinding.ItemHomeSortBinding
import com.seom.banchan.ui.adapter.viewholder.ModelViewHolder
import com.seom.banchan.ui.adapter.viewholder.home.SortViewHolder
import com.seom.banchan.ui.model.CellType
import com.seom.banchan.ui.model.Model
import com.seom.banchan.util.listener.ModelAdapterListener
import com.seom.banchan.util.mapper.ModelViewHolderMapper
import com.seom.banchan.util.provider.ResourceProvider

class ModelRecyclerAdapter<M : Model>(
    private val resourceProvider: ResourceProvider? = null,
    private val modelAdapterListener: ModelAdapterListener? = null
) : RecyclerView.Adapter<ModelViewHolder<M>>() {

    // recyclerview 에 보여줄 list
    private var modelList: MutableList<Model> = mutableListOf()
    override fun getItemCount() = modelList.size

    override fun getItemViewType(position: Int) = modelList[position].type.ordinal

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ModelViewHolder<M> {
        if (CellType.values()[viewType] == CellType.SORT_CELL) {
            val sortViewHolder = SortViewHolder(
                ItemHomeSortBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
            sortViewHolder.initViewHolder()
            return sortViewHolder as ModelViewHolder<M>
        }
        return ModelViewHolderMapper.map(parent, CellType.values()[viewType], resourceProvider)
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

    fun updateList(list: List<Model>, startIndex: Int) {
        modelList = (modelList.subList(0, startIndex) + list).toMutableList()
        notifyItemRangeChanged(modelList.size - list.size, modelList.size - 1)
    }

    fun updateModelAtPosition(model: Model, position: Int) {
        if (modelList.size <= position) modelList.add(position, model)
        else modelList[position] = model
        notifyItemChanged(position)
    }

    fun updateModelsAtPosition(list: List<Model>, startIndex: Int, endIndex: Int) {
        modelList = (modelList.subList(0, startIndex) + list + modelList.subList(
            modelList.size - 3,
            modelList.size
        )).toMutableList()
        notifyItemRangeChanged(0,modelList.size-2)
    }
}