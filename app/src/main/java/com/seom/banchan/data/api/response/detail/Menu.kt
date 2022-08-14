package com.seom.banchan.data.api.response.detail


import com.google.gson.annotations.SerializedName

data class Menu(
    @SerializedName("delivery_fee")
    val deliveryFee: String?, // 배달 가격(string)
    @SerializedName("delivery_info")
    val deliveryInfo: String?, // 배달 정보
    @SerializedName("detail_section")
    val detailSection: List<String>?, // 상세 이미지
    @SerializedName("point")
    val point: String?, // 적립금
    @SerializedName("prices")
    val prices: List<String>, // 가격(원가, 할인가)
    @SerializedName("product_description")
    val productDescription: String?, // 메뉴 이름
    @SerializedName("thumb_images")
    val thumbImages: List<String>?, // 썸네일 이미지
    @SerializedName("top_image")
    val topImage: String? // ??
)