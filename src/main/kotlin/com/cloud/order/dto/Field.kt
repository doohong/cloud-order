package com.cloud.order.dto

import java.io.Serializable

data class Field (
    var type: String = "",
    var optional: Boolean = true,
    var field: String = "",
)