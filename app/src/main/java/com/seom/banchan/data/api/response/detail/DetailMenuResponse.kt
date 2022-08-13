package com.seom.banchan.data.api.response.detail


import com.google.gson.annotations.SerializedName

/**
 * 메뉴 상세 정보 서버 요청 reponse
 */
data class DetailMenuResponse(
    @SerializedName("data")
    val data: Data?,
    @SerializedName("hash")
    val hash: String?
)