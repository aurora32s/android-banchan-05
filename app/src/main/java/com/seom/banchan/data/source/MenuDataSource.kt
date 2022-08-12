package com.seom.banchan.data.source

import com.seom.banchan.domain.model.CategoryModel
import com.seom.banchan.domain.model.MenuModel

/**
 * 메뉴에만 관련된 요청 관리
 */
interface MenuDataSource {
    suspend fun getBestMenus(): Result<List<CategoryModel>>

    suspend fun getMainMenus(): Result<List<MenuModel>>

    suspend fun getSoupMenus(): Result<List<MenuModel>>

    suspend fun getSideMenus(): Result<List<MenuModel>>
}