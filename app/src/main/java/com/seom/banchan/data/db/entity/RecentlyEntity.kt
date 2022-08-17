package com.seom.banchan.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.seom.banchan.domain.model.home.MenuModel

@Entity(tableName = "recently_table")
data class RecentlyEntity (
    @PrimaryKey @ColumnInfo(name = "recently_id") val id : String,
    @ColumnInfo(name = "image") val image : String,
    @ColumnInfo(name = "title") val title : String,
    @ColumnInfo(name = "normal_price") val normalPrice : Int,
    @ColumnInfo(name = "sale_price") val salePrice : Int,
    @ColumnInfo(name = "recently_time") val recentlyTime : Long
)

fun List<RecentlyEntity>.toMenuModelList() : List<MenuModel> {
    return map {
        MenuModel(
            id = it.id,
            deliveryType = emptyList(),
            description = "",
            image = it.image,
            salePrice = it.salePrice,
            normalPrice = it.normalPrice,
            name = it.title,
            recentlyTime = it.recentlyTime
        )
    }
}
fun MenuModel.toRecentlyEntity(recentlyTime : Long) : RecentlyEntity {
    return RecentlyEntity(
        id = id,
        image = image,
        salePrice = salePrice,
        normalPrice = normalPrice,
        title = name,
        recentlyTime = recentlyTime
    )
}