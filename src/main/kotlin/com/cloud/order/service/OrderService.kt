package com.cloud.order.service


import com.cloud.order.dto.OrderDto
import com.cloud.order.entity.OrderEntity
import com.cloud.order.entity.OrderRepository
import org.modelmapper.ModelMapper
import org.modelmapper.convention.MatchingStrategies
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Service
import java.util.*

@Service
class OrderService(
    private val orderRepository: OrderRepository,
) {

    fun createOrder(orderDto: OrderDto): OrderDto {
        orderDto.orderId = UUID.randomUUID().toString()
        orderDto.totalPrice = orderDto.qty * orderDto.unitPrice

        val mapper = ModelMapper()
        mapper.configuration.matchingStrategy = MatchingStrategies.STRICT

        val orderEntity = mapper.map(orderDto, OrderEntity::class.java)

        orderRepository.save(orderEntity)

        return mapper.map(orderEntity, OrderDto::class.java)
    }

    fun getOrdersByUserId(userId: String): Iterable<OrderEntity> {
        return orderRepository.findByUserId(userId)
    }
}