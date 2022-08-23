package com.seom.banchan.domain.usecase

import com.seom.banchan.di.IODispatcher
import com.seom.banchan.domain.repository.OrderRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

// HiltWorker 에서는 Singleton Component 만 주입이 가능
@Singleton
class UpdateDeliveryStateUseCase @Inject constructor(
    @IODispatcher
    private val ioDispatcher: CoroutineDispatcher,
    private val orderRepository: OrderRepository
) {
    suspend operator fun invoke(orderId: Long): Result<Int> = withContext(ioDispatcher) {
        orderRepository.setDeliveryCompletedById(orderId)
    }
}