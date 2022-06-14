package com.wendelnunes.order.config

import com.wendelnunes.order.service.OrderService
import com.wendelnunes.order.stream.consumer.OrderShippingStatusEventConsumer
import com.wendelnunes.order.stream.producer.OrderCanceledEventProducer
import com.wendelnunes.order.stream.producer.OrderShippingRequestEventProducer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class KafkaConfig {

    @Bean
    fun orderShippingRequestProducer(): OrderShippingRequestEventProducer {
        return OrderShippingRequestEventProducer()
    }

    @Bean
    fun orderCanceledProducer(): OrderCanceledEventProducer {
        return OrderCanceledEventProducer()
    }

    @Bean
    fun orderShippingStatusConsumer(orderService: OrderService): OrderShippingStatusEventConsumer {
        return OrderShippingStatusEventConsumer(orderService)
    }
}