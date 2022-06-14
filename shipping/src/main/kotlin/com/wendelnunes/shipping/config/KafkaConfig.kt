package com.wendelnunes.shipping.config

import com.wendelnunes.shipping.service.OrderShippingService
import com.wendelnunes.shipping.stream.consumer.OrderCanceledEventConsumer
import com.wendelnunes.shipping.stream.consumer.OrderShippingRequestEventConsumer
import com.wendelnunes.shipping.stream.producer.OrderShippingStatusEventProducer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class KafkaConfig {

    @Bean
    fun orderShippingRequestConsumer(orderShippingService: OrderShippingService) = OrderShippingRequestEventConsumer(orderShippingService)

    @Bean
    fun orderCanceledConsumer(orderShippingService: OrderShippingService) = OrderCanceledEventConsumer(orderShippingService)

    @Bean
    fun orderShippingStatusProducer() = OrderShippingStatusEventProducer()
}