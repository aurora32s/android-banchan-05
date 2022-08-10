package com.seom.banchan.ui.adapter.viewholder

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.seom.banchan.ui.model.Model

abstract class ModelViewHolder<M : Model>(
    binding: ViewBinding
) : RecyclerView.ViewHolder(binding.root) {
    open fun bind(model: M) {}
}