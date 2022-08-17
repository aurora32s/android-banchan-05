package com.seom.banchan.domain.usecase

import com.seom.banchan.di.IODispatcher
import com.seom.banchan.domain.model.cart.CartMenuModel
import com.seom.banchan.domain.repository.CartRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AddMenuToCartUseCase @Inject constructor(
    private val cartRepository: CartRepository,
    @IODispatcher
    private val ioDispatcher: CoroutineDispatcher
) {
    suspend operator fun invoke(menu: CartMenuModel): Result<Long> = withContext(ioDispatcher) {
        cartRepository.addOrReplaceMenuToCart(menu)
    }
}