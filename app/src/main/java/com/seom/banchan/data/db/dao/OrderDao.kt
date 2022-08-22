package com.seom.banchan.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.seom.banchan.data.db.entity.OrderEntity
import com.seom.banchan.data.db.entity.OrderItemEntity
import com.seom.banchan.data.db.entity.OrderListEntity
import kotlinx.coroutines.flow.Flow

/**
 * 주문 내역 관련 DB
 */
@Dao
interface OrderDao {

    /**
     * 주문 내역 추가
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrder(order: OrderEntity): Long

    /**
     * 주문 아이템들 추가
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrderItem(orderItems: List<OrderItemEntity>)

    /**
     * 주문 내역 리스트 요청
     */
    @Query(
        "select\n" +
                "  o.order_id as order_id,\n" +
                "  i.name as menu_name,\n" +
                "  i.image as image,\n" +
                "  sum(i.sale_price * i.count) as total_price,\n" +
                "  sum(count) as menu_count,\n" +
                "  o.delivery_type as delivery_type\n" +
                "from order_table o, order_item_table i\n" +
                "where o.order_id = i.order_id\n" +
                "group by o.order_id"
    )
    fun getOrderList(): Flow<List<OrderListEntity>>
}