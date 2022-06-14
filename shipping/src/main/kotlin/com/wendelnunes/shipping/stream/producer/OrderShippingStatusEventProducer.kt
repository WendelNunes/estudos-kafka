package com.wendelnunes.shipping.stream.producer

import com.wendelnunes.shipping.domain.OrderShipping
import com.wendelnunes.shipping.stream.producer.dto.OrderShippingStatusDTO
import com.wendelnunes.shipping.stream.producer.mapper.OrderShippingStatusMapper
import org.slf4j.LoggerFactory
import org.springframework.messaging.Message
import org.springframework.messaging.support.MessageBuilder
import org.springframework.stereotype.Component
import reactor.core.publisher.Flux
import reactor.core.publisher.Sinks
import java.util.function.Supplier

@Component
class OrderShippingStatusEventProducer : Supplier<Flux<Message<OrderShippingStatusDTO>>> {
    companion object {
        private val LOGGER = LoggerFactory.getLogger(this::class.java)
        private val sink = Sinks.many().unicast().onBackpressureBuffer<Message<OrderShippingStatusDTO>>()
    }

    fun produce(orderShipping: OrderShipping) {
        sink.emitNext(
            MessageBuilder
                .withPayload(OrderShippingStatusMapper.toDTO(orderShipping))
                .build(),
            Sinks.EmitFailureHandler.FAIL_FAST
        )
    }

    override fun get(): Flux<Message<OrderShippingStatusDTO>> {
        return sink.asFlux()
            .doOnNext { LOGGER.info("Sending order shipping status: {}", it) }
            .doOnError { LOGGER.info("Error sending order shipping status: {}", it) }
    }
}