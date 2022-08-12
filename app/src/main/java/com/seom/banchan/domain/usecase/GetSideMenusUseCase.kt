package com.seom.banchan.domain.usecase

import com.seom.banchan.di.IODispatcher
import com.seom.banchan.domain.model.CategoryModel
import com.seom.banchan.domain.model.MenuModel
import com.seom.banchan.domain.repository.MenuRepository
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * 카테고리가 있는 메뉴 리스트 요청
 */
@ViewModelScoped
class GetSideMenusUseCase @Inject constructor(
    private val menuRepository: MenuRepository,
    @IODispatcher
    private val ioDispatcher: CoroutineDispatcher
) {
    suspend operator fun invoke(): Result<List<MenuModel>> = withContext(ioDispatcher) {
        menuRepository.getSideMenus()
    }
}