package com.wendelnunes.shipping.web.api.v1.mapper

import com.wendelnunes.shipping.domain.OrderShipping
import com.wendelnunes.shipping.web.api.v1.dto.OrderShippingDTO
import com.wendelnunes.shipping.web.api.v1.dto.OrderShippingDTO.StatusDTO

object OrderShippingMapper {

    fun toDTO(orderShipping: OrderShipping) = OrderShippingDTO(
        id = orderShipping.id,
        orderId = orderShipping.orderId,
        status = orderShipping.status?.let { StatusDTO.valueOf(it.name) }
    )
}