package com.seom.banchan.data.api.response

import com.google.gson.annotations.SerializedName
import com.seom.banchan.domain.model.MenuModel
import com.seom.banchan.util.ext.toIntPrice

data class Menu(
    @SerializedName("detail_hash")
    val detailHash: String,
    val alt: String?,
    val title: String,
    @SerializedName("delivery_type")
    val deliveryType: List<String>?,
    val badge: List<String>?,
    val image: String?,
    val description: String?,
    @SerializedName("s_price")
    val sPrice: String?, // 할인가
    @SerializedName("n_price")
    val nPrice: String? // 원가
)

fun Menu.toModel() = MenuModel(
    id = detailHash,
    name = title,
    deliveryType = deliveryType ?: emptyList(),
    image = image ?: "",
    description = description ?: "",
    salePrice = sPrice.toIntPrice(),
    normalPrice = nPrice.toIntPrice()
)