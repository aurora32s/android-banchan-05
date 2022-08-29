package com.seom.banchan.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.seom.banchan.ui.model.Model

open class DiffItemCallback<M : Model> : DiffUtil.ItemCallback<M>() {
    override fun areItemsTheSame(oldItem:M, newItem:M): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: M, newItem: M): Boolean {
        return oldItem.equals(newItem)
    }
}