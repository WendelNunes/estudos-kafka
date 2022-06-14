package com.wendelnunes.order.web.api.v1.dto

import java.math.BigDecimal
import java.time.LocalDateTime

data class OrderDTO(
    val id: String? = null,
    val date: LocalDateTime? = null,
    val itens: List<ItemDTO>? = null,
    val status: StatusDTO? = null,
    val shippingId: String?,
    val statusShipping: StatusShippingDTO?
) {
    enum class StatusDTO {
        ACTIVE, CANCELED;
    }

    data class ItemDTO(
        val idProduct: String? = null,
        val quantity: BigDecimal? = null,
    )

    enum class StatusShippingDTO {
        PROCESSING, SENT, CANCELED;
    }
}