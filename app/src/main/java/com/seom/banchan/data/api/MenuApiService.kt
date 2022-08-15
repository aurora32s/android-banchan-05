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
     * @param (detail_hash) 메뉴 hash id
     */
    @GET("detail/{detail_hash}")
    suspend fun getMenuDetail(@Path("detail_hash") id: String): Response<DetailMenuResponse>
}