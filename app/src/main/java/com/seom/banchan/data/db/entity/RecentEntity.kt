package com.seom.banchan.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.seom.banchan.domain.model.home.MenuModel
import com.seom.banchan.util.TimeUtil

@Entity(tableName = "recent_table")
data class RecentEntity(
    @PrimaryKey @ColumnInfo(name = "recent_id") val id: String,
    @ColumnInfo(name = "image") val image: String,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "normal_price") val normalPrice: Int,
    @ColumnInfo(name = "sale_price") val salePrice: Int,
    @ColumnInfo(name = "recent_time") val recentTime: Long = System.currentTimeMillis()
)

fun RecentEntity.toMenuModel(): MenuModel =
    MenuModel(
        id = id,
        deliveryType = emptyList(),
        description = "",
        image = image,
        salePrice = salePrice,
        normalPrice = normalPrice,
        name = title,
        recentTime = TimeUtil.getTimeData(recentTime)
    )


fun MenuModel.toRecentEntity(): RecentEntity =
    RecentEntity(
        id = id,
        image = image,
        salePrice = salePrice,
        normalPrice = normalPrice,
        title = name
    )
