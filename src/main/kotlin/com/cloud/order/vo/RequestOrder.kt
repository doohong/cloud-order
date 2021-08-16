package com.cloud.order.vo

import javax.validation.constraints.Email
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

data class RequestOrder(
    val productId: String,
    val qty: Int,
    val unitPrice: Int
)