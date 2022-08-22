package com.seom.banchan.domain.usecase

import com.seom.banchan.domain.repository.CartRepository
import javax.inject.Inject

class UpdateCartMenuListSelectedUseCase @Inject constructor(
    private val cartRepository: CartRepository
) {
    suspend operator fun invoke(menuIds: List<String>) : Int {
        return cartRepository.updateCartMenuListSelected(menuIds)
    }
}