package com.cloud.order.dto

import java.io.Serializable

data class Payload(
  var productId: String = "",
  var qty: Int = 0,
  var unitPrice: Int = 0,
  var totalPrice: Int = 0,
  var orderId: String = "",
  var userId: String = "",
)