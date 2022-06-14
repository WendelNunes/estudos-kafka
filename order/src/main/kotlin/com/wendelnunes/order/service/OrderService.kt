package com.wendelnunes.order.service

import com.wendelnunes.order.domain.Order
import com.wendelnunes.order.domain.Order.Shipping
import com.wendelnunes.order.domain.Order.Status
import com.wendelnunes.order.repository.OrderRepository
import com.wendelnunes.order.stream.producer.OrderCanceledEventProducer
import com.wendelnunes.order.stream.producer.OrderShippingRequestEventProducer
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import reactor.core.publisher.Mono

@Service
class OrderService(
    private val repository: OrderRepository,
    private val orderShippingRequestEventProducer: OrderShippingRequestEventProducer,
    private val orderCanceledEventProducer: OrderCanceledEventProducer
) {
    companion object {
        private val LOGGER = LoggerFactory.getLogger(this::class.java)
    }

    @Transactional(rollbackFor = [Exception::class])
    fun create(order: Order): Mono<Order> {
        order.status = Status.ACTIVE

        return this.repository.save(order)
            .doOnSuccess(orderShippingRequestEventProducer::produce)
    }

    fun updateShippingStatus(shipping: Shipping) =
        this.findById(shipping.orderId ?: "")
            .map {
                it.shipping = shipping
                it
            }.flatMap(repository::save)

    @Transactional(rollbackFor = [Exception::class])
    fun deleteById(id: String) {
        this.findById(id)
            .doOnNext { LOGGER.info("achou a ordem") }
            .map {
                it.status = Status.CANCELED
                it
            }
            .flatMap(repository::save)
            .doOnSuccess(orderCanceledEventProducer::produce)
            .subscribe()
    }

    fun findById(id: String) = this.repository.findById(id)

    fun findAll() = this.repository.findAll()
}