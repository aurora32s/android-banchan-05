package com.seom.banchan.data.repository

import com.seom.banchan.data.api.SortCriteria
import com.seom.banchan.data.source.MenuDataSource
import com.seom.banchan.domain.model.detail.DetailMenuModel
import com.seom.banchan.domain.model.home.CategoryModel
import com.seom.banchan.domain.model.home.MenuModel
import com.seom.banchan.domain.repository.MenuRepository
import javax.inject.Inject


class MenuRepositoryImpl @Inject constructor(
    private val menuDataSource: MenuDataSource
) : MenuRepository {
    override suspend fun getBestMenus(): Result<List<CategoryModel>> {
        return menuDataSource.getBestMenus()
    }

    override suspend fun getMainMenus(sortCriteria: SortCriteria): Result<List<MenuModel>>  {
        return menuDataSource.getMainMenus(sortCriteria)
    }

    override suspend fun getSoupMenus(sortCriteria: SortCriteria): Result<List<MenuModel>>  {
        return menuDataSource.getSoupMenus(sortCriteria)
    }

    override suspend fun getSideMenus(sortCriteria : SortCriteria): Result<List<MenuModel>>  {
        return menuDataSource.getSideMenus(sortCriteria)
    }

    // 특정 menu 의 상세 정보 요청
    override suspend fun getMenuDetail(menuId: String): Result<DetailMenuModel> {
        return menuDataSource.getMenuDetail(menuId)
    }
}