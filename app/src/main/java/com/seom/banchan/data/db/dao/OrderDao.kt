package com.seom.banchan.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.seom.banchan.data.db.entity.*
import com.seom.banchan.data.source.OrderDataSource
import com.seom.banchan.ui.model.order.OrderDeliveryState
import com.seom.banchan.worker.model.DeliveryAlarmModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

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
        "select " +
                "  o.order_id as order_id, " +
                "  i.name as menu_name, " +
                "  i.image as image, " +
                "  sum(i.sale_price * i.count) as total_price, " +
                "  sum(count) as menu_count, " +
                "  o.delivery_state as delivery_state " +
                "from order_table o, order_item_table i " +
                "where o.order_id = i.order_id " +
                "group by o.order_id"
    )
    fun getOrderList(): Flow<List<OrderListEntity>>

    @Query(
        "select " +
                "  order_id as order_id, " +
                "  menu_name as menu_name, " +
                "  total_count as total_count, " +
                "  expected_time as expected_time, " +
                "  o.createdAt as createdAt " +
                "from order_table o, ( " +
                "  select " +
                "    name as menu_name, " +
                "    sum(count) as total_count " +
                "  from order_item_table " +
                "  where order_id = :orderId " +
                ") " +
                "where order_id = :orderId"
    )
    fun getDeliveryInfoById(orderId: Long): DeliveryInfoEntity

    @Query(
        "select " +
                "  o.order_id as order_id," +
                "  i.name as menu_name," +
                "  sum(i.count) as total_count," +
                "  o.expected_time as expected_time, " +
                "  o.createdAt as createdAt " +
                "from order_table o, order_item_table i " +
                "where o.order_id = i.order_id " +
                "and o.delivery_state = :deliveryType " +
                "group by o.order_id"
    )
    fun getAllDeliveryInfo(deliveryType: Int = OrderDeliveryState.DELIVERING.type): List<DeliveryInfoEntity>

    /**
     * 특정 주문 id 의 주문 정보 받아오기
     */
    @Query("SELECT * FROM order_table WHERE order_id = :orderId")
    fun getOrderById(orderId: Long): Flow<OrderEntity>

    /**
     * 특정 주문 id 의 메뉴 정보 받아오기
     */
    @Query("SELECT * FROM order_item_table WHERE order_id = :orderId")
    suspend fun getOrderMenuById(orderId: Long): List<OrderItemEntity>

    @Transaction
    suspend fun getOrderInfoById(orderId: Long): OrderInfoEntity {
        return OrderInfoEntity(
            order = getOrderById(orderId),
            menus = getOrderMenuById(orderId)
        )
    }

    /**
     * 특정 주문 배달 완료 처리
     */
    @Query("UPDATE order_table SET delivery_state = :deliveryComplete WHERE order_id = :orderId")
    suspend fun setDeliveryCompletedById(
        orderId: Long,
        deliveryComplete: Int = OrderDeliveryState.DELIVERED.type
    ): Int
}