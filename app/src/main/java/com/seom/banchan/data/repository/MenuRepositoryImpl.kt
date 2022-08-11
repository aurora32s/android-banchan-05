package com.seom.banchan.data.repository

import com.seom.banchan.data.source.MenuDataSource
import com.seom.banchan.domain.model.CategoryModel
import com.seom.banchan.domain.repository.MenuRepository

class MenuRepositoryImpl(
    private val menuDataSource: MenuDataSource
) : MenuRepository {
    override suspend fun getBestMenus(): Result<List<CategoryModel>> {
        return menuDataSource.getBestMenus()
    }
}