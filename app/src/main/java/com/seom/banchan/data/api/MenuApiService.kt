package com.seom.banchan.data.api

import com.seom.banchan.data.api.response.BestMenuResponse
import retrofit2.Response
import retrofit2.http.GET

interface MenuApiService {
    @GET("/best")
    suspend fun getBestMenus(): Response<BestMenuResponse>
}