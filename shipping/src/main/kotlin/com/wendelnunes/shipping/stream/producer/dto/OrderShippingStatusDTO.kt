package com.wendelnunes.shipping.stream.producer.dto

data class OrderShippingStatusDTO(
    val shippingId: String?,
    val orderId: String?,
    val status: StatusDTO?
) {
    enum class StatusDTO {
        PROCESSING, SENT, CANCELED;
    }
}