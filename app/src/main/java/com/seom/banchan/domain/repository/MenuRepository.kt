package com.seom.banchan.domain.repository

import com.seom.banchan.domain.model.detail.DetailMenuModel
import com.seom.banchan.domain.model.home.CategoryModel
import com.seom.banchan.domain.model.home.MenuModel


interface MenuRepository {
    /**
     * 기획전 메뉴 정보 요청
     */
    suspend fun getBestMenus(): Result<List<CategoryModel>>

    suspend fun getMainMenus(): Result<List<MenuModel>>

    suspend fun getSoupMenus(): Result<List<MenuModel>>

    suspend fun getSideMenus(): Result<List<MenuModel>>

    /**
     * 특정 메뉴의 상세 정보 요청
     * @param (String) menuId: 상세 정보를 요청할 menu 의 id
     */
    suspend fun getMenuDetail(menuId: String): Result<DetailMenuModel>
}