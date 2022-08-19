package com.seom.banchan.domain.usecase

import com.seom.banchan.domain.repository.CartRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCartMenusIdUseCase @Inject constructor(
    private val cartRepository: CartRepository
) {
    operator fun invoke(): Flow<List<String>> {
        return cartRepository.getCartMenusId()
    }
}