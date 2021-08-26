package com.cloud.order.dto

import java.io.Serializable

class KafkaOrderDto : Serializable {
  var schema: Schema = Schema()
  var payload: Payload = Payload()
}