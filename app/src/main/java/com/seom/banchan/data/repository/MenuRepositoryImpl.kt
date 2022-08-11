package com.seom.banchan.data.repository

import android.util.Log
import com.seom.banchan.data.source.MenuDataSource
import com.seom.banchan.domain.model.CategoryModel
import com.seom.banchan.domain.repository.MenuRepository
import javax.inject.Inject

class MenuRepositoryImpl @Inject constructor(
    private val menuDataSource: MenuDataSource
) : MenuRepository {
    override suspend fun getBestMenus(): Result<List<CategoryModel>> {
        val response = menuDataSource.getBestMenus()
        Log.d("Repository", response.toString())
        return menuDataSource.getBestMenus()
    }
}