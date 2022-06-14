package com.wendelnunes.order.web.api.v1.mapper

import com.wendelnunes.order.domain.Order
import com.wendelnunes.order.domain.Order.*
import com.wendelnunes.order.domain.Order.Shipping.StatusShipping
import com.wendelnunes.order.web.api.v1.dto.OrderDTO
import com.wendelnunes.order.web.api.v1.dto.OrderDTO.ItemDTO
import com.wendelnunes.order.web.api.v1.dto.OrderDTO.StatusDTO

object OrderMapper {

    fun toDTO(order: Order) = OrderDTO(
        id = order.id,
        date = order.date,
        itens = order.itens
            ?.map(this::toItemDTO),
        status = order.status?.let { StatusDTO.valueOf(it.name) },
        shippingId = order.shipping?.id,
        statusShipping = order.shipping?.status?.let { OrderDTO.StatusShippingDTO.valueOf(it.name) }
    )

    private fun toItemDTO(item: Item) = ItemDTO(
        idProduct = item.idProduct,
        quantity = item.quantity
    )

    fun toEntity(orderDTO: OrderDTO) = Order(
        id = orderDTO.id,
        date = orderDTO.date,
        itens = orderDTO.itens
            ?.map(this::toItemEntity),
        status = orderDTO.status?.let { Status.valueOf(it.name) },
        shipping = if (orderDTO.shippingId != null || orderDTO.statusShipping != null) toShipping(orderDTO) else null

    )

    private fun toShipping(orderDTO: OrderDTO) = Shipping(
        id = orderDTO.shippingId,
        status = orderDTO.statusShipping?.let { StatusShipping.valueOf(it.name) }
    )

    private fun toItemEntity(itemDTO: ItemDTO) = Item(
        idProduct = itemDTO.idProduct,
        quantity = itemDTO.quantity
    )
}