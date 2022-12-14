package com.seom.banchan.domain.usecase

import com.seom.banchan.di.IODispatcher
import com.seom.banchan.domain.model.order.OrderItemModel
import com.seom.banchan.domain.repository.OrderRepository
import com.seom.banchan.worker.model.DeliveryAlarmModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * 장바구니에 담긴 메뉴 담기
 */
class AddOrderUseCase @Inject constructor(
    @IODispatcher
    private val ioDispatcher: CoroutineDispatcher,
    private val orderRepository: OrderRepository
) {
    suspend operator fun invoke(orderItems: List<OrderItemModel>): Result<DeliveryAlarmModel> =
        withContext(ioDispatcher) {
            orderRepository.addOrder(orderItems)
        }
}