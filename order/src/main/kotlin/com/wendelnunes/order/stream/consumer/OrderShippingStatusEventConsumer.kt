package com.wendelnunes.order.stream.consumer

import com.wendelnunes.order.service.OrderService
import com.wendelnunes.order.stream.consumer.dto.OrderShippingStatusDTO
import com.wendelnunes.order.stream.consumer.mapper.ShippingMapper
import org.slf4j.LoggerFactory
import org.springframework.messaging.Message
import java.util.function.Consumer

class OrderShippingStatusEventConsumer(
    private val orderService: OrderService
) : Consumer<Message<OrderShippingStatusDTO>> {
    companion object {
        private val LOGGER = LoggerFactory.getLogger(this::class.java);
    }

    override fun accept(t: Message<OrderShippingStatusDTO>) {
        LOGGER.info("Order shipping status received: {}", t.payload)

        val orderShippingStatusDTO = t.payload

        if (orderShippingStatusDTO.orderId != null
            && orderShippingStatusDTO.shippingId != null
            && orderShippingStatusDTO.status != null
        ) {
            this.orderService
                .updateShippingStatus(ShippingMapper.toEntity(orderShippingStatusDTO))
                .subscribe()
        }
    }
}