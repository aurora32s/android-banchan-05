package com.seom.banchan.data.source.remote

import com.seom.banchan.data.api.MenuApiService
import com.seom.banchan.data.api.response.best.toModel
import com.seom.banchan.data.source.MenuDataSource
import com.seom.banchan.domain.model.CategoryModel
import com.seom.banchan.domain.model.MenuModel
import javax.inject.Inject

class MenuDataSourceImpl @Inject constructor(
    private val menuApiService: MenuApiService
) : MenuDataSource {
    override suspend fun getBestMenus(): Result<List<CategoryModel>> = try {
        val response = menuApiService.getBestMenus()
        if (response.isSuccessful) {
            val result = response.body()
            if (result != null) {
                Result.success(result.body.map { category -> category.toModel() })
            } else {
                Result.success(emptyList())
            }
        } else {
            throw Exception()
        }
    } catch (exception: Exception) {
        Result.failure<Nothing>(exception)
    }

    override suspend fun getMainMenus(): Result<List<MenuModel>> = try {
        val response = menuApiService.getMainMenus()
        if (response.isSuccessful) {
            val result = response.body()
            if (result != null) {
                Result.success(result.body.map { menu -> menu.toModel() })
            } else {
                Result.success(emptyList())
            }
        } else {
            throw Exception()
        }
    } catch (exception: Exception) {
        Result.failure<Nothing>(exception)
    }

    override suspend fun getSoupMenus(): Result<List<MenuModel>> = try {
        val response = menuApiService.getSoupMenus()
        if (response.isSuccessful) {
            val result = response.body()
            if (result != null) {
                Result.success(result.body.map { menu -> menu.toModel() })
            } else {
                Result.success(emptyList())
            }
        } else {
            throw Exception()
        }
    } catch (exception: Exception) {
        Result.failure<Nothing>(exception)
    }

    override suspend fun getSideMenus(): Result<List<MenuModel>> = try {
        val response = menuApiService.getSideMenus()
        if (response.isSuccessful) {
            val result = response.body()
            if (result != null) {
                Result.success(result.body.map { menu -> menu.toModel() })
            } else {
                Result.success(emptyList())
            }
        } else {
            throw Exception()
        }
    } catch (exception: Exception) {
        Result.failure<Nothing>(exception)
    }
}