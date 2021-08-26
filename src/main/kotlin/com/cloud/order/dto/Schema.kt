package com.cloud.order.dto

import java.io.Serializable

data class Schema(
  var type: String = "",
  var fields: List<Field> = listOf(),
  var optional: Boolean = true,
  var name: String = "",
)