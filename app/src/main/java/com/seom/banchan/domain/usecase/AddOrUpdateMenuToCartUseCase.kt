package com.seom.banchan.domain.usecase

import com.seom.banchan.di.IODispatcher
import com.seom.banchan.domain.model.cart.CartMenuModel
import com.seom.banchan.domain.repository.CartRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * 장바구니에 메뉴를 추가하거나 이미 있는 메뉴라면 개수를 더하고 교체한다.
 */
class AddOrUpdateMenuToCartUseCase @Inject constructor(
    private val cartRepository: CartRepository,
    @IODispatcher
    private val ioDispatcher: CoroutineDispatcher
) {
    suspend operator fun invoke(menu: CartMenuModel): Result<Long> = withContext(ioDispatcher) {
        cartRepository.addOrUpdateMenuToCart(menu)
    }
}