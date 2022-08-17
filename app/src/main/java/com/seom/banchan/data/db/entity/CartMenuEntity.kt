package com.seom.banchan.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CartMenuEntity(
    @PrimaryKey
    val id: Int,
    val menuId: String,
    val name: String,
    val image: String,
    val salePrice: Int,
    val count: Int
)
