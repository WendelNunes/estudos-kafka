package com.wendelnunes.shipping.stream.consumer.mapper

import com.wendelnunes.shipping.domain.OrderShipping
import com.wendelnunes.shipping.domain.OrderShipping.Status.PROCESSING
import com.wendelnunes.shipping.stream.consumer.dto.OrderShippingRequestDTO

object OrderShippingRequestMapper {

    fun toEntity(orderShippingRequestDTO: OrderShippingRequestDTO) = OrderShipping(
        orderId = orderShippingRequestDTO.id,
        status = PROCESSING
    )
}