package com.cloud.order.messageque

import com.cloud.order.dto.OrderDto
import com.fasterxml.jackson.core.JsonParseException
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service

@Service
class KafkaProducer(
    private val kafkaTemplate: KafkaTemplate<String, String>
) {
  fun send(topic: String, orderDto: OrderDto): OrderDto {
    val mapper = ObjectMapper()
    var jsonInString = "";
    try {
      jsonInString = mapper.writeValueAsString(orderDto)
    } catch (e: JsonParseException) {
      e.printStackTrace()
    }
    kafkaTemplate.send(topic, jsonInString)
    return orderDto
  }
}