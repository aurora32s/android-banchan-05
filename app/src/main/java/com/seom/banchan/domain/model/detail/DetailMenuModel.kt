package com.seom.banchan.domain.model.detail

import com.seom.banchan.domain.model.home.MenuModel

/**
 * 메뉴 상세 정보 domain model
 */
data class DetailMenuModel(
    val id: String, // menu id
    val name: String, // menu 이름
    val thumbnail: List<String>?, // menu 썸네일(첫 이미지가 대표 이미지)
    val point: Int, // 적립금
    val salePrice: Int, // 판매금
    val normalPrice: Int, // 원가
    val deliveryInfo: String?, // 배송 정보
    val deliveryFee: String?, // 배송비(String)
    val detailImage: List<String>? // 상세 이미지
)
