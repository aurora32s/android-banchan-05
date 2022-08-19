package com.seom.banchan.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.seom.banchan.domain.model.cart.CartMenuModel

@Entity(tableName = "cart_table")
data class CartMenuEntity(
    @PrimaryKey
    @ColumnInfo(name = "menu_id") val menuId: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "image") val image: String?,
    @ColumnInfo(name = "sale_price") val salePrice: Int,
    @ColumnInfo(name = "count") val count: Int
)

fun CartMenuEntity.toModel() = CartMenuModel(
    menuId = menuId,
    name = name,
    image = image,
    salePrice = salePrice,
    count = count
)