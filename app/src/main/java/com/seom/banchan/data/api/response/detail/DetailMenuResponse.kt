package com.seom.banchan.data.api.response.detail


import com.google.gson.annotations.SerializedName
import com.seom.banchan.domain.model.detail.DetailMenuModel
import com.seom.banchan.util.ext.toIntPrice

/**
 * 메뉴 상세 정보 서버 요청 reponse
 */
data class DetailMenuResponse(
    @SerializedName("data")
    val data: Data,
    @SerializedName("hash")
    val hash: String
)

/**
 * domain model 로 변경
 */
fun DetailMenuResponse.toModel() = DetailMenuModel(
    id = hash,
    name = data.productDescription ?: "UnKnown",
    thumbnail = data.thumbImages,
    point = data.point?.toInt() ?: 0,
    salePrice = getSalePrice(),
    normalPrice = getNormalPrice(),
    deliveryInfo = data.deliveryInfo,
    deliveryFee = data.deliveryFee,
    detailImage = data.detailSection
)

/**
 * 가격 데이터의 크기가 1인 경우, index = 0이 sale price
 * 가격 데이터의 크기가 2인 경우, index = 0이 normal price, index =1이 sale price
 */
fun DetailMenuResponse.getSalePrice() =
    (if (data.prices.size > 1) data.prices[1] else data.prices[0]).toIntPrice()

fun DetailMenuResponse.getNormalPrice() =
    if (data.prices.size > 1) data.prices[0].toIntPrice() else 0