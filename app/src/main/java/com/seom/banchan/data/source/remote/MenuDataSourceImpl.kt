package com.seom.banchan.data.source.remote

import com.seom.banchan.data.api.MenuApiService
import com.seom.banchan.data.api.SortCriteria
import com.seom.banchan.data.api.requestApi
import com.seom.banchan.data.api.response.best.toModel
import com.seom.banchan.data.api.response.detail.toModel
import com.seom.banchan.data.source.MenuDataSource
import com.seom.banchan.domain.model.detail.DetailMenuModel
import com.seom.banchan.domain.model.home.CategoryModel
import com.seom.banchan.domain.model.home.MenuModel
import com.seom.banchan.util.ext.EmptyResponseException
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

    override suspend fun getMainMenus(sortCriteria: SortCriteria): Result<List<MenuModel>> = try {
        val response = menuApiService.getMainMenus()
        if (response.isSuccessful) {
            val result = response.body()
            if (result != null) {
                Result.success(result.body.map { menu -> menu.toModel() }.sortByCriteria(sortCriteria))
            } else {
                Result.success(emptyList())
            }
        } else {
            throw Exception()
        }
    } catch (exception: Exception) {
        Result.failure<Nothing>(exception)
    }

    override suspend fun getSoupMenus(sortCriteria: SortCriteria): Result<List<MenuModel>> = try {
        val response = menuApiService.getSoupMenus()
        if (response.isSuccessful) {
            val result = response.body()
            if (result != null) {
                Result.success(result.body.map { menu -> menu.toModel() }.sortByCriteria(sortCriteria))
            } else {
                Result.success(emptyList())
            }
        } else {
            throw Exception()
        }
    } catch (exception: Exception) {
        Result.failure<Nothing>(exception)
    }

    override suspend fun getSideMenus(sortCriteria: SortCriteria): Result<List<MenuModel>> = try {
        val response = menuApiService.getSideMenus()
        if (response.isSuccessful) {
            val result = response.body()
            if (result != null) {
                Result.success(result.body.map { menu -> menu.toModel() }.sortByCriteria(sortCriteria))
            } else {
                Result.success(emptyList())
            }
        } else {
            throw Exception()
        }
    } catch (exception: Exception) {
        Result.failure<Nothing>(exception)
    }

    // 특정 메뉴의 상세 정보 요청
    override suspend fun getMenuDetail(id: String): Result<DetailMenuModel> {
        return try {
            val response = requestApi { menuApiService.getMenuDetail(id) }
            Result.success(response.toModel())
        } catch (exception: EmptyResponseException) {
            // 특정 menu id 정보를 찾지 못했다면 exception 발생
            Result.failure<Nothing>(exception)
        } catch (exception: Exception) {
            // remote api 통신 중 문제가 발생한 exception
            Result.failure<Nothing>(exception)
        }
    }
}
fun List<MenuModel>.sortByCriteria(sortCriteria: SortCriteria) : List<MenuModel>{
    return when(sortCriteria){
        SortCriteria.ASCENDING -> sortedBy {
           it.salePrice
        }
        SortCriteria.DESCENDING -> sortedByDescending {
            it.salePrice
        }
        SortCriteria.DISCOUNT_RATE -> sortedByDescending {
            if (it.salePrice == 0) 0 else Math.ceil((1 - (it.salePrice / it.normalPrice.toDouble())) * 100).toInt()
        }
        SortCriteria.BASE -> this
    }
}