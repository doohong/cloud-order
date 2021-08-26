package com.cloud.order.dto

import java.io.Serializable

class Schema : Serializable {
  var type: String = ""
  var fields: List<Field> = listOf()
  var optional: Boolean = true
  var name: String = ""
}