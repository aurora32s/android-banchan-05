package com.seom.banchan.domain.usecase

import androidx.paging.PagingData
import com.seom.banchan.domain.model.home.MenuModel
import com.seom.banchan.domain.repository.RecentRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRecentMenusPagingUseCase @Inject constructor(
    private val recentRepository: RecentRepository
) {
    operator fun invoke() : Flow<PagingData<MenuModel>> {
        return recentRepository.getRecentsPaging()
    }
}