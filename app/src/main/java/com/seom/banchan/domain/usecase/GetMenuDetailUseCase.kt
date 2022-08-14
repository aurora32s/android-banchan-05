package com.seom.banchan.domain.usecase

import com.seom.banchan.di.IODispatcher
import com.seom.banchan.domain.repository.MenuRepository
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

@ViewModelScoped
class GetMenuDetailUseCase @Inject constructor(
    private val menuRepository: MenuRepository,
    @IODispatcher
    private val ioDispatcher: CoroutineDispatcher
) {
    suspend operator fun invoke(menuId: String) = withContext(ioDispatcher) {
        menuRepository.getMenuDetail(menuId)
    }
}