package com.wendelnunes.order.stream.producer

import com.wendelnunes.order.domain.Order
import com.wendelnunes.order.stream.producer.dto.OrderShippingRequestDTO
import com.wendelnunes.order.stream.producer.mapper.OrderMapper
import org.slf4j.LoggerFactory
import org.springframework.messaging.Message
import org.springframework.messaging.support.MessageBuilder
import org.springframework.stereotype.Component
import reactor.core.publisher.Flux
import reactor.core.publisher.Sinks
import java.util.function.Supplier

@Component
class OrderShippingRequestEventProducer : Supplier<Flux<Message<OrderShippingRequestDTO>>> {
    companion object {
        private val LOGGER = LoggerFactory.getLogger(this::class.java)
        private val sink = Sinks.many().unicast().onBackpressureBuffer<Message<OrderShippingRequestDTO>>()
    }

    fun produce(order: Order) {
        sink.emitNext(
            MessageBuilder
                .withPayload(OrderMapper.toDTO(order))
                .build(),
            Sinks.EmitFailureHandler.FAIL_FAST
        )
    }

    override fun get(): Flux<Message<OrderShippingRequestDTO>> {
        return sink.asFlux()
            .doOnNext { LOGGER.info("Sending order for shipping: {}", it) }
            .doOnError { LOGGER.info("Error sending order for shipping: {}", it) }
    }
}