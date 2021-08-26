package com.cloud.order.dto

import java.io.Serializable

class KafkaOrderDto(
    var schema: Schema = Schema(),
    var payload: Payload = Payload()
) : Serializable {

}