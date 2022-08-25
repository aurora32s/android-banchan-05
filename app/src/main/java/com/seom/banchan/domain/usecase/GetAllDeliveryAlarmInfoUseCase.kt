package com.seom.banchan.domain.usecase

import com.seom.banchan.di.IODispatcher
import com.seom.banchan.domain.repository.OrderRepository
import com.seom.banchan.worker.model.DeliveryAlarmModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetAllDeliveryAlarmInfoUseCase @Inject constructor(
    @IODispatcher
    private val ioDispatcher: CoroutineDispatcher,
    private val orderRepository: OrderRepository
) {
    suspend operator fun invoke(): Result<List<DeliveryAlarmModel>> = withContext(ioDispatcher) {
        orderRepository.getAllDeliveryInfo()
    }
}