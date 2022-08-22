package com.seom.banchan.domain.usecase

import com.seom.banchan.domain.repository.CartRepository
import javax.inject.Inject

class DeleteCartMenuUseCase @Inject constructor(
    private val cartRepository: CartRepository
) {
    suspend operator fun invoke(menuId: String) : Int {
        return cartRepository.deleteCartMenu(menuId)
    }
}