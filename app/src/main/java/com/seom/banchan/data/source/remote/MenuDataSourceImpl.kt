package com.seom.banchan.data.source.remote

import com.seom.banchan.data.api.MenuApiService
import com.seom.banchan.data.api.response.BestMenuResponse
import com.seom.banchan.data.api.response.toModel
import com.seom.banchan.data.source.MenuDataSource
import com.seom.banchan.domain.model.CategoryModel
import javax.inject.Inject

class MenuDataSourceImpl(
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
}