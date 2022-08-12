package com.seom.banchan.data.api

import com.seom.banchan.data.api.response.BestMenuResponse
import com.seom.banchan.data.api.response.MenuResponse
import retrofit2.Response
import retrofit2.http.GET

interface MenuApiService {
    @GET("best")
    suspend fun getBestMenus(): Response<BestMenuResponse>

    @GET("main")
    suspend fun getMainMenus(): Response<MenuResponse>

    @GET("soup")
    suspend fun getSoupMenus(): Response<MenuResponse>

    @GET("side")
    suspend fun getSideMenus(): Response<MenuResponse>
}