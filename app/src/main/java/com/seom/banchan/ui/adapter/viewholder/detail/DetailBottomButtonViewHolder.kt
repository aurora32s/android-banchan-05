package com.seom.banchan.ui.adapter.viewholder.detail

import androidx.core.view.doOnAttach
import androidx.lifecycle.findViewTreeLifecycleOwner
import com.seom.banchan.databinding.ItemDetailBottomButtonBinding
import com.seom.banchan.ui.adapter.viewholder.ModelViewHolder
import com.seom.banchan.ui.model.detail.DetailBottomButtonModel
import com.seom.banchan.util.ext.repeatLaunch
import com.seom.banchan.util.listener.ModelAdapterListener
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailBottomButtonViewHolder(
    private val binding: ItemDetailBottomButtonBinding
) : ModelViewHolder<DetailBottomButtonModel>(binding) {

    private lateinit var model: DetailBottomButtonModel

    init {
        itemView.doOnAttach {
            if (::model.isInitialized) initObserver(model)
        }
    }

    override fun bindData(model: DetailBottomButtonModel) {
        this.model = model
    }

    private fun initObserver(model: DetailBottomButtonModel) {
        binding.root.findViewTreeLifecycleOwner()?.repeatLaunch {
            model.totalCount.collect {
                binding.totalPrice = it
            }
        }
    }

    override fun bindViews(
        model: DetailBottomButtonModel,
        menuAdapterListener: ModelAdapterListener?
    ) {
        binding.btnAddCart.setOnClickListener {
            menuAdapterListener?.onClick(it, model, adapterPosition)
        }
    }
}