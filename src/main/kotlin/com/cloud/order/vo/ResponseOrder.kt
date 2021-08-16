package com.cloud.order.vo

import java.time.LocalDate
import javax.validation.constraints.Email
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

data class ResponseOrder(
    var productId: String = "",
    var qty: Int = 0,
    var unitPrice: Int = 0,
    var totalPrice: Int = 0,
    var createdAt: LocalDate = LocalDate.now(),

    var orderId: String = "",
)