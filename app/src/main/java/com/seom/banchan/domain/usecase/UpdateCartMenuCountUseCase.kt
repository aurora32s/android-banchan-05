package com.seom.banchan.domain.usecase

import com.seom.banchan.domain.repository.CartRepository
import javax.inject.Inject

class UpdateCartMenuCountUseCase @Inject constructor(
    private val cartRepository: CartRepository
) {
    suspend operator fun invoke(menuId: String, count : Int) : Int {
        return cartRepository.updateCartMenuCount(menuId, count)
    }
}