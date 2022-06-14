package com.wendelnunes.order.stream.producer

import com.wendelnunes.order.domain.Order
import org.slf4j.LoggerFactory
import org.springframework.messaging.Message
import org.springframework.messaging.support.MessageBuilder
import org.springframework.stereotype.Component
import reactor.core.publisher.Flux
import reactor.core.publisher.Sinks
import java.util.function.Supplier

@Component
class OrderCanceledEventProducer : Supplier<Flux<Message<String>>> {
    companion object {
        private val LOGGER = LoggerFactory.getLogger(this::class.java)
        private val sink = Sinks.many().unicast().onBackpressureBuffer<Message<String>>()
    }

    fun produce(order: Order) {
        sink.emitNext(
            MessageBuilder
                .withPayload(order.id ?: "")
                .build(),
            Sinks.EmitFailureHandler.FAIL_FAST
        )
    }

    override fun get(): Flux<Message<String>> {
        return sink.asFlux()
            .doOnNext { LOGGER.info("Order canceled event sent: {}", it) }
            .doOnError { LOGGER.info("Error order canceled event sent: {}", it) }
    }
}