package com.cloud.order.entity

import org.springframework.data.repository.CrudRepository

interface OrderRepository : CrudRepository<OrderEntity, Long> {
    fun findByOrderId(orderId: String) : OrderEntity?
    fun findByUserId(userId: String) : Iterable<OrderEntity>
}