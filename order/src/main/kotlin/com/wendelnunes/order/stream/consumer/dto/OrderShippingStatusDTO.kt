package com.wendelnunes.order.stream.consumer.dto

data class OrderShippingStatusDTO(
    val shippingId: String?,
    val orderId: String?,
    val status: StatusDTO?
) {
    enum class StatusDTO {
        PROCESSING, SENT, CANCELED;
    }
}