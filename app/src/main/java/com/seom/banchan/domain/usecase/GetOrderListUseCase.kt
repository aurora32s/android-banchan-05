package com.seom.banchan.domain.usecase

import com.seom.banchan.di.IODispatcher
import com.seom.banchan.domain.model.order.OrderListModel
import com.seom.banchan.domain.repository.OrderRepository
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ViewModelScoped
class GetOrderListUseCase @Inject constructor(
    private val orderRepository: OrderRepository
) {

    /**
     * 모든 주문 내역 요청
     */
    operator fun invoke(): Flow<List<OrderListModel>> {
        return orderRepository.getOrderList()
    }
}