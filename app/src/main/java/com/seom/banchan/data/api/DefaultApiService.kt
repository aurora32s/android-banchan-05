package com.seom.banchan.data.api

import com.seom.banchan.util.ext.EmptyResponseException
import retrofit2.Response

suspend fun <T> requestApi(service: suspend () -> Response<T>): T{
    val response = service()
    if (response.isSuccessful) {
        val result = response.body()
        if (result != null) {
            return result
        }
        throw EmptyResponseException()
    }
    throw Exception()
}