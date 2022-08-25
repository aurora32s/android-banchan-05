package com.seom.banchan.domain.model.detail

import com.seom.banchan.ui.model.detail.DetailMenuUiModel
import com.seom.banchan.ui.model.detail.MenuDetailModel
import java.lang.Math.ceil

/**
 * 메뉴 상세 정보 domain model
 */
data class DetailMenuModel(
    val id: String, // menu id
    val description: String, // menu description
    val thumbnail: List<String>?, // menu 썸네일(첫 이미지가 대표 이미지)
    val point: Int, // 적립금
    val salePrice: Int, // 판매금
    val normalPrice: Int, // 원가
    val deliveryInfo: String?, // 배송 정보
    val deliveryFee: String?, // 배송비(String)
    val detailImage: List<String>? // 상세 이미지
) {
    val discountRate: Int =
        if (normalPrice == 0) 0
        else ceil((1 - (salePrice / normalPrice.toDouble())) * 100).toInt()
}

fun DetailMenuModel.toUiModel(menuName: String) = DetailMenuUiModel(
    detailMenu = MenuDetailModel(
        id = id,
        images = thumbnail,
        name = menuName,
        description = description,
        point = point,
        deliveryInfo = deliveryInfo,
        deliveryFee = deliveryFee,
        normalPrice = normalPrice,
        salePrice = salePrice,
        detailImages = detailImage
    ),
    discountRate = discountRate
)
