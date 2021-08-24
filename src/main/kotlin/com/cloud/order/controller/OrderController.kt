package com.cloud.order.controller

import com.cloud.order.dto.OrderDto
import com.cloud.order.messageque.KafkaProducer
import com.cloud.order.service.OrderService
import com.cloud.order.vo.RequestOrder
import com.cloud.order.vo.ResponseOrder
import org.modelmapper.ModelMapper
import org.modelmapper.convention.MatchingStrategies
import org.springframework.core.env.Environment
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/")
class OrderController(
    private val env: Environment,
    private val orderService: OrderService,
    private val kafkaProducer: KafkaProducer,
) {
    @GetMapping("/health_check")
    fun status(): String {

        return "It's Working in User Service" +
            ", port =  ${env.getProperty("local.server.port")}" +
            ", token secret =  ${env.getProperty("token.secret")}" +
            ", token expiration time =  ${env.getProperty("token.expiration_time")}"
    }


    @GetMapping("/{userId}/orders")
    fun getOrders(@PathVariable("userId") userId: String): ResponseEntity<List<ResponseOrder>> {
        val orderEntities = orderService.getOrdersByUserId(userId)
        val mapper = ModelMapper()
        return ResponseEntity.status(HttpStatus.OK).body(orderEntities.map {
            mapper.map(it, ResponseOrder::class.java)
        })
    }


    @PostMapping("/{userId}/orders")
    fun createUser(@RequestBody order: RequestOrder, @PathVariable("userId") userId: String): ResponseEntity<Any>{
        val mapper = ModelMapper()
        mapper.configuration.matchingStrategy = MatchingStrategies.STRICT
        val orderDto = mapper.map(order, OrderDto::class.java)
        orderDto.userId = userId

        val responseOrderDto = orderService.createOrder(orderDto)
        val responseOrder = mapper.map(responseOrderDto, ResponseOrder::class.java)

        kafkaProducer.send("example-category-topic", orderDto)
        return ResponseEntity.status(HttpStatus.CREATED).body(responseOrder)
    }
}