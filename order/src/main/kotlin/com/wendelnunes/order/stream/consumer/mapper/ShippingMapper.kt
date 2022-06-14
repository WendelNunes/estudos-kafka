package com.wendelnunes.order.stream.consumer.mapper

import com.wendelnunes.order.domain.Order
import com.wendelnunes.order.domain.Order.Shipping.StatusShipping
import com.wendelnunes.order.stream.consumer.dto.OrderShippingStatusDTO

object ShippingMapper {

    fun toEntity(orderShippingStatusDTO: OrderShippingStatusDTO) = Order.Shipping(
        id = orderShippingStatusDTO.shippingId,
        orderId = orderShippingStatusDTO.orderId,
        status = orderShippingStatusDTO.status?.let { StatusShipping.valueOf(it.name) }
    )
}