package com.seom.banchan.data.api

import com.seom.banchan.data.api.response.MenuResponse
import com.seom.banchan.data.api.response.best.BestMenuResponse
import com.seom.banchan.data.api.response.detail.DetailMenuResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface MenuApiService {
    @GET("best")
    suspend fun getBestMenus(): Response<BestMenuResponse>

    @GET("main")
    suspend fun getMainMenus(): Response<MenuResponse>

    @GET("soup")
    suspend fun getSoupMenus(): Response<MenuResponse>

    @GET("side")
    suspend fun getSideMenus(): Response<MenuResponse>

    /**
     * 메뉴 상세 정보 요청
     * detail_hash: 요청할 메뉴의 hash 값
     */
    @GET("detail/{detail_hash}")
    suspend fun getMenuDetail(@Path("detail_hash") menuId: String): Response<DetailMenuResponse>
}