package com.cloud.order.dto

import java.io.Serializable

class Field : Serializable {
  var type: String = ""
  var optional: Boolean = true
  var field: String = ""
}