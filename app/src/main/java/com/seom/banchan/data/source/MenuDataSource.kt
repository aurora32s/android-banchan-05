package com.seom.banchan.data.source

import com.seom.banchan.domain.model.detail.DetailMenuModel
import com.seom.banchan.domain.model.home.CategoryModel
import com.seom.banchan.domain.model.home.MenuModel

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

    /**
     * 특정 메뉴의 상세 정보 요청청
     * @param (id) 메뉴 hash id
     */
    suspend fun getMenuDetail(id: String): Result<DetailMenuModel>
}