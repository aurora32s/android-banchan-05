package com.seom.banchan.domain.usecase

import com.seom.banchan.domain.repository.CartRepository
import javax.inject.Inject

class UpdateCartMenuCountDecreaseUseCase @Inject constructor(
    private val cartRepository: CartRepository
) {
    suspend operator fun invoke(menuId: String) : Int {
        return cartRepository.updateCartMenuCountDecrease(menuId)
    }
}