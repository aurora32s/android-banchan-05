package com.seom.banchan.domain.repository

import com.seom.banchan.domain.model.home.MenuModel
import kotlinx.coroutines.flow.Flow

interface RecentRepository {
    /*
   최근 본 상품 전체 목록 조회
    */
    suspend fun getRecents(): Flow<List<MenuModel>>

    /*
    최근 본 상품 목록 조회 최대 7개
    */
    suspend fun getLatestRecents(): Flow<List<MenuModel>>

    /*
    최근 본 상품 목록 삽입 혹은 수정
     */
    suspend fun upsertRecent(menuModel: MenuModel)
}