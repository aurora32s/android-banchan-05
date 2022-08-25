package com.seom.banchan.ui.adapter.viewholder.detail

import androidx.core.view.doOnAttach
import androidx.lifecycle.findViewTreeLifecycleOwner
import com.seom.banchan.databinding.ItemDetailCountBinding
import com.seom.banchan.ui.adapter.viewholder.ModelViewHolder
import com.seom.banchan.ui.model.detail.MenuCountModel
import com.seom.banchan.util.ext.repeatLaunch
import com.seom.banchan.util.listener.ModelAdapterListener
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MenuCountViewHolder(
    private val binding: ItemDetailCountBinding
) : ModelViewHolder<MenuCountModel>(binding) {

    private lateinit var model: MenuCountModel

    init {
        itemView.doOnAttach {
            if (::model.isInitialized) initObserve(model)
        }
    }

    override fun bindData(model: MenuCountModel) {
        this.model = model
    }

    private fun initObserve(model: MenuCountModel) {
        binding.root.findViewTreeLifecycleOwner()?.repeatLaunch {
            model.count.collect { binding.count = it }
        }
    }

    override fun bindViews(
        model: MenuCountModel,
        menuAdapterListener: ModelAdapterListener?
    ) {
        binding.ivUpCount.setOnClickListener {
            menuAdapterListener?.onClick(it, model, adapterPosition)
        }
        binding.ivDownCount.setOnClickListener {
            menuAdapterListener?.onClick(it, model, adapterPosition)
        }
    }
}