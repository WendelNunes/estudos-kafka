package com.wendelnunes.shipping.domain

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "order-shippings")
data class OrderShipping(

    @Id
    val id: String? = null,
    val orderId: String? = null,
    var status: Status? = null
) {
    enum class Status {
        PROCESSING, SENT, CANCELED;
    }
}