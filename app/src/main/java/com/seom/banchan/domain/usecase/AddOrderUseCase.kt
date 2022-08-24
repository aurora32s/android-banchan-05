package com.seom.banchan.domain.usecase

import com.seom.banchan.di.IODispatcher
import com.seom.banchan.domain.model.order.OrderItemModel
import com.seom.banchan.domain.repository.OrderRepository
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
    suspend operator fun invoke(orderItems: List<OrderItemModel>): Result<Long> =
        withContext(ioDispatcher) {
            orderRepository.addOrder(orderItems)
        }
}