package com.seom.banchan.domain.usecase

import com.seom.banchan.di.IODispatcher
import com.seom.banchan.domain.repository.OrderRepository
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * 주문 내역 상세 요청 use case
 */
@ViewModelScoped
class GetDetailOrderInfoUseCase @Inject constructor(
    @IODispatcher
    private val ioDispatcher: CoroutineDispatcher,
    private val orderRepository: OrderRepository
) {
    suspend operator fun invoke(orderId: Long) = withContext(ioDispatcher) {
        orderRepository.getDetailOrderInfo(orderId)
    }
}