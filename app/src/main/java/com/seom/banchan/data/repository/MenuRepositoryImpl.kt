package com.seom.banchan.data.repository

import com.seom.banchan.data.source.MenuDataSource
import com.seom.banchan.domain.model.CategoryModel
import com.seom.banchan.domain.model.MenuModel
import com.seom.banchan.domain.repository.MenuRepository
import javax.inject.Inject

class MenuRepositoryImpl @Inject constructor(
    private val menuDataSource: MenuDataSource
) : MenuRepository {
    override suspend fun getBestMenus(): Result<List<CategoryModel>> {
        return menuDataSource.getBestMenus()
    }

    override suspend fun getMainMenus(): Result<List<MenuModel>>  {
        return menuDataSource.getMainMenus()
    }

    override suspend fun getSoupMenus(): Result<List<MenuModel>>  {
        return menuDataSource.getSoupMenus()
    }

    override suspend fun getSideMenus(): Result<List<MenuModel>>  {
        return menuDataSource.getSideMenus()
    }
}