package com.seom.banchan.domain.usecase

import com.seom.banchan.di.IODispatcher
import com.seom.banchan.domain.model.MenuModel
import com.seom.banchan.domain.repository.RecentlyRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetRecentlyMenusUseCase @Inject constructor(
    private val recentlyRepository: RecentlyRepository,
    @IODispatcher
    private val ioDispatcher: CoroutineDispatcher
) {
    suspend operator fun invoke() : Flow<List<MenuModel>> = withContext(ioDispatcher){
        recentlyRepository.getRecentlys()
    }
}