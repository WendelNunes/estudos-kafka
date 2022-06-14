package com.wendelnunes.shipping.stream.consumer

import com.wendelnunes.shipping.service.OrderShippingService
import com.wendelnunes.shipping.stream.consumer.dto.OrderShippingRequestDTO
import com.wendelnunes.shipping.stream.consumer.mapper.OrderShippingRequestMapper
import org.slf4j.LoggerFactory
import org.springframework.messaging.Message
import java.util.function.Consumer

class OrderShippingRequestEventConsumer(
    private val orderShippingService: OrderShippingService
) : Consumer<Message<OrderShippingRequestDTO>> {
    companion object {
        private val LOGGER = LoggerFactory.getLogger(this::class.java)
    }

    override fun accept(t: Message<OrderShippingRequestDTO>) {
        LOGGER.info("Order shipping received: {}", t.payload)
        this.orderShippingService
            .save(OrderShippingRequestMapper.toEntity(t.payload))
            .subscribe()
    }
}