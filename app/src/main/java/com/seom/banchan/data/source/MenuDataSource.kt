package com.seom.banchan.data.source

import com.seom.banchan.domain.model.CategoryModel
import com.seom.banchan.domain.model.MenuModel

/**
 * 메뉴와 관련된 요청 관리
 */
interface MenuDataSource {
    /**
     * 서버로부터 '기확전' 데이터 요청
     */
    suspend fun getBestMenus(): Result<List<CategoryModel>>

    suspend fun getMainMenus(): Result<List<MenuModel>>

    suspend fun getSoupMenus(): Result<List<MenuModel>>

    suspend fun getSideMenus(): Result<List<MenuModel>>
}