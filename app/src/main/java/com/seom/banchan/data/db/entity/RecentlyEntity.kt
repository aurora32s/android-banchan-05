package com.seom.banchan.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.seom.banchan.domain.model.home.MenuModel
import com.seom.banchan.util.TimeUtil

@Entity(tableName = "recently_table")
data class RecentlyEntity(
    @PrimaryKey @ColumnInfo(name = "recently_id") val id: String,
    @ColumnInfo(name = "image") val image: String,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "normal_price") val normalPrice: Int,
    @ColumnInfo(name = "sale_price") val salePrice: Int,
    @ColumnInfo(name = "recently_time") val recentlyTime: Long = System.currentTimeMillis()
)

fun RecentlyEntity.toMenuModel(): MenuModel =
    MenuModel(
        id = id,
        deliveryType = emptyList(),
        description = "",
        image = image,
        salePrice = salePrice,
        normalPrice = normalPrice,
        name = title,
        recentlyTime = TimeUtil.getTimeData(recentlyTime)
    )


fun MenuModel.toRecentlyEntity(recentlyTime: Long): RecentlyEntity =
    RecentlyEntity(
        id = id,
        image = image,
        salePrice = salePrice,
        normalPrice = normalPrice,
        title = name
    )
