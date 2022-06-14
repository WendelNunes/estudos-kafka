package com.wendelnunes.order.domain

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.math.BigDecimal
import java.time.LocalDateTime

@Document(collection = "orders")
data class Order(
    @Id
    val id: String? = null,
    val date: LocalDateTime? = null,
    val itens: List<Item>? = null,
    var status: Status? = null,
    var shipping: Shipping?
) {
    enum class Status {
        ACTIVE, CANCELED;
    }

    data class Item(
        val idProduct: String? = null,
        val quantity: BigDecimal? = null,
        val price: BigDecimal? = null
    )

    data class Shipping(
        val id: String? = null,
        val orderId: String? = null,
        var status: StatusShipping? = null,
    ) {
        enum class StatusShipping {
            PROCESSING, SENT, CANCELED;
        }
    }
}
