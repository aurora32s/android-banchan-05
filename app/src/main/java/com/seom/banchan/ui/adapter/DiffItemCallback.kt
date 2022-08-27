package com.seom.banchan.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.seom.banchan.ui.model.Model

open class DiffItemCallback<M : Model>(
    private val onAreItemsTheSame : (M, M) -> Boolean,
    private val onAreContentsTheSame : (M, M) -> Boolean
) : DiffUtil.ItemCallback<M>() {
    override fun areItemsTheSame(oldItem: M, newItem: M): Boolean {
        return onAreItemsTheSame(oldItem,newItem)
    }

    override fun areContentsTheSame(oldItem: M, newItem: M): Boolean {
        return onAreContentsTheSame(oldItem,newItem)
    }
}