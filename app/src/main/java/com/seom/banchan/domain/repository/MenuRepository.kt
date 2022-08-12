package com.seom.banchan.domain.repository

import com.seom.banchan.domain.model.CategoryModel
import com.seom.banchan.domain.model.MenuModel

interface MenuRepository {
    /**
     * 기획전 메뉴 정보 요청
     */
    suspend fun getBestMenus(): Result<List<CategoryModel>>

    suspend fun getMainMenus(): Result<List<MenuModel>>

    suspend fun getSoupMenus(): Result<List<MenuModel>>

    suspend fun getSideMenus(): Result<List<MenuModel>>
}