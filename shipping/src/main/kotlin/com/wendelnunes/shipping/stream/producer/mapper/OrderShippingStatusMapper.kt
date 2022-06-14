package com.wendelnunes.shipping.stream.producer.mapper

import com.wendelnunes.shipping.domain.OrderShipping
import com.wendelnunes.shipping.stream.producer.dto.OrderShippingStatusDTO
import com.wendelnunes.shipping.stream.producer.dto.OrderShippingStatusDTO.StatusDTO

object OrderShippingStatusMapper {

    fun toDTO(orderShipping: OrderShipping) = OrderShippingStatusDTO(
        shippingId = orderShipping.id,
        orderId = orderShipping.orderId,
        status = orderShipping.status?.let { StatusDTO.valueOf(it.name) }
    )
}