package com.wendelnunes.shipping.service

import com.wendelnunes.shipping.domain.OrderShipping
import com.wendelnunes.shipping.domain.OrderShipping.Status
import com.wendelnunes.shipping.repository.OrderShippingRepository
import com.wendelnunes.shipping.stream.producer.OrderShippingStatusEventProducer
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import reactor.core.publisher.Mono

@Service
class OrderShippingService(
    private val repository: OrderShippingRepository,
    private val orderShippingStatusEventProducer: OrderShippingStatusEventProducer
) {
    companion object {
        private val LOGGER = LoggerFactory.getLogger(this::class.java)
    }

    @Transactional(rollbackFor = [Exception::class])
    fun save(orderShipping: OrderShipping): Mono<OrderShipping> {
        return this.repository.save(orderShipping)
            .doOnNext { LOGGER.info("Order shipping saved: {}", it) }
            .doOnSuccess(orderShippingStatusEventProducer::produce)
    }

    fun markAsSent(id: String) = this.findById(id)
        .map {
            it.status = Status.SENT
            it
        }.flatMap(this::save)

    fun findById(id: String) = this.repository.findById(id)

    fun findAll() = this.repository.findAll()

    fun deleteByOrderId(orderId: String) = this.findByOrderId(orderId)
        .map {
            it.status = Status.CANCELED
            it
        }
        .flatMap(repository::save)
        .doOnSuccess(orderShippingStatusEventProducer::produce)

    fun findByOrderId(orderId: String) = this.repository.findByOrderId(orderId);
}