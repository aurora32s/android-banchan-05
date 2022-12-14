package com.seom.banchan.ui.adapter.viewholder.order

import androidx.core.view.doOnAttach
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.lifecycle.findViewTreeLifecycleOwner
import com.seom.banchan.R
import com.seom.banchan.databinding.ItemOrderStateBinding
import com.seom.banchan.ui.adapter.viewholder.ModelViewHolder
import com.seom.banchan.ui.model.order.OrderDeliveryState
import com.seom.banchan.ui.model.order.OrderStateUiModel
import com.seom.banchan.util.ext.repeatLaunch
import com.seom.banchan.util.ext.setIconDrawable
import com.seom.banchan.util.ext.toDate
import com.seom.banchan.util.provider.ResourceProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.math.ceil

/**
 * 배달 상태를 나타내는 viewHolder
 */
class OrderDeliveryStateViewHolder(
    private val binding: ItemOrderStateBinding,
    private val resourceProvider: ResourceProvider?
) : ModelViewHolder<OrderStateUiModel>(binding) {

    private lateinit var model: OrderStateUiModel

    init {
        itemView.doOnAttach {
            if (::model.isInitialized) initObserver(model)
        }
    }

    override fun bindData(model: OrderStateUiModel) {
        binding.ivAppIcon.setIconDrawable(R.drawable.ic_app_icon)
        binding.tvMenuCount.text = "${model.menuCount}개"
        this.model = model
    }

    private fun initObserver(model: OrderStateUiModel) {
        binding.root.findViewTreeLifecycleOwner()?.repeatLaunch {
            launch { // 배달 상태 정보 업데이트
                model.orderDeliveryState.collect {
                    binding.tvOrderState.text = resourceProvider?.getString(it.stateLongTitle) ?: ""

                    if (it == OrderDeliveryState.DELIVERED) {
                        binding.tvExtraDeliveryTimeTitle.text =
                            resourceProvider?.getString(R.string.order_delivered_completed) ?: ""
                    }
                }
            }
            launch { // 남은 시간 계산
                model.extraTime.collect { setDeliveryTime(it, model.expectedDeliveryTime) }
            }
        }
    }

    /**
     * time: 남은 배송 시간
     * expectedTime: 예상 배송 완료 시간
     */
    private fun setDeliveryTime(time: Long, expectedTime: Long) {
        binding.pbExtraDeliveryTime.progress =
            (ceil((expectedTime - time) / expectedTime.toDouble() * 100)).toInt()
        binding.tvExtraDeliveryTime.text = if (time > 0) time.toDate() else expectedTime.toDate()
    }
}