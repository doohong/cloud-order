package com.cloud.order.entity

import org.hibernate.annotations.ColumnDefault
import java.io.Serializable
import java.time.LocalDate
import javax.persistence.*

@Entity
@Table(name="orders")
class OrderEntity : Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0

    @Column(nullable = false, length = 120, unique = true)
    var productId: String = ""

    @Column(nullable = false)
    var qty: Int = 0

    @Column(nullable = false)
    var unitPrice: Int = 0

    @Column(nullable = false)
    var totalPrice: Int = 0


    @Column(nullable = false)
    var userId: String = ""

    @Column(nullable = false)
    var orderId: String = ""

    @Column(nullable = false, updatable = false, insertable = false)
    @ColumnDefault(value = "CURRENT_TIMESTAMP")
    var createdAt: LocalDate = LocalDate.now()
}