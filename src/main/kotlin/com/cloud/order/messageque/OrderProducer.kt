package com.cloud.order.messageque

import com.cloud.order.dto.*
import com.fasterxml.jackson.core.JsonParseException
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service

@Service
class OrderProducer(
    private val kafkaTemplate: KafkaTemplate<String, String>
) {
  val fields = listOf(
      Field("string", true, "order_id"),
      Field("string", true, "user_id"),
      Field("string", true, "product_id"),
      Field("string", true, "qty"),
      Field("string", true, "unit_price"),
      Field("string", true, "total_price")
  )
  val schema = Schema("struct", fields, false, "orders")
  fun send(topic: String, orderDto: OrderDto): OrderDto {
    val payload = Payload(
        orderId = orderDto.orderId,
        userId = orderDto.userId,
        productId = orderDto.productId,
        qty = orderDto.qty,
        unitPrice = orderDto.unitPrice,
        totalPrice = orderDto.totalPrice
    )
    val kafkaOrderDto = KafkaOrderDto(schema, payload)
    val mapper = ObjectMapper()
    var jsonInString = "";
    try {
      jsonInString = mapper.writeValueAsString(kafkaOrderDto)
    } catch (e: JsonParseException) {
      e.printStackTrace()
    }
    kafkaTemplate.send(topic, jsonInString)
    return orderDto
  }
}