package com.cloud.order.messageque

import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.StringSerializer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.core.ProducerFactory

@Configuration
class KafkaProducerConfig {
  @Bean
  fun producerFactory(): ProducerFactory<String, String> {
    val properties = mapOf(
        ProducerConfig.BOOTSTRAP_SERVERS_CONFIG to "127.0.0.1:9092",
        ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG to StringSerializer::class,
        ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG to StringSerializer::class
    )
    return DefaultKafkaProducerFactory(properties)
  }

  @Bean
  fun kafkaTemplate(): KafkaTemplate<String, String> {
    return KafkaTemplate(producerFactory())
  }
}