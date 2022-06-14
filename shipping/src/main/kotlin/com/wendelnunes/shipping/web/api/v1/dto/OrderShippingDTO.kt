package com.wendelnunes.shipping.web.api.v1.dto

data class OrderShippingDTO(
    val id: String?,
    val orderId: String?,
    val status: StatusDTO?,
) {
    enum class StatusDTO {
        PROCESSING, SENT, CANCELED
    }
}