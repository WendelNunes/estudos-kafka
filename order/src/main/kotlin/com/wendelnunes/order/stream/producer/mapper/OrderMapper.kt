package com.wendelnunes.order.stream.producer.mapper

import com.wendelnunes.order.domain.Order
import com.wendelnunes.order.stream.producer.dto.OrderShippingRequestDTO

object OrderMapper {

    fun toDTO(order: Order) = OrderShippingRequestDTO(
        id = order.id
    )
}