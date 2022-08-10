package com.seom.banchan.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.seom.banchan.databinding.ItemHomeHeaderBinding
import com.seom.banchan.ui.model.home.UiModel

class HomeAdapter :RecyclerView.Adapter<ModelViewHolder>() {
    private var itemList = listOf<UiModel>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ModelViewHolder {
        return ModelViewHolder(ItemHomeHeaderBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ModelViewHolder, position: Int) {
        holder.bind(itemList[position] as UiModel.CategoryTitle)
    }

    override fun getItemCount() = itemList.size

    fun submitList(list: List<UiModel>){
        itemList = list
    }
}

class ModelViewHolder(val binding: ItemHomeHeaderBinding) : RecyclerView.ViewHolder(binding.root){
    fun bind(model : UiModel.CategoryTitle) {
        binding.tvHeader.text = model.title
    }
}