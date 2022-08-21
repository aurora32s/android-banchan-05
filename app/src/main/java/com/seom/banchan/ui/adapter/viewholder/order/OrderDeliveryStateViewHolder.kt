package com.seom.banchan.ui.adapter.viewholder.order

import com.seom.banchan.databinding.ItemOrderStateBinding
import com.seom.banchan.ui.adapter.viewholder.ModelViewHolder
import com.seom.banchan.ui.model.order.OrderStateUiModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

/**
 * 배달 상태를 나타내는 viewHolder
 */
class OrderDeliveryStateViewHolder(
    private val binding: ItemOrderStateBinding
) : ModelViewHolder<OrderStateUiModel>(binding) {

    override fun bindData(model: OrderStateUiModel) {
        CoroutineScope(Dispatchers.Main).launch {
            // 배달 상태 정보 업데이트
            model.orderDeliveryState.collect {
                binding.tvOrderState.text = binding.root.context.getString(it.stateLongTitle)
            }
        }
        CoroutineScope(Dispatchers.Main).launch {
            model.extraTime.collect {
                // 남은 시간 계산
                setDeliveryTime(it, model.expectedDeliveryTime)
            }
        }
    }

    /**
     * time: 배송이 시작되고 지난 시간
     * expectedTime: 예상 배송 완료 시간
     */
    private fun setDeliveryTime(time: Long, expectedTime: Long) {
        binding.pbExtraDeliveryTime.progress =
            ((expectedTime - time) / expectedTime.toDouble() * 100).toInt()
    }
}