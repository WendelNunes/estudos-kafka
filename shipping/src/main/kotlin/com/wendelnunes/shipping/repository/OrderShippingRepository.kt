package com.wendelnunes.shipping.repository

import com.wendelnunes.shipping.domain.OrderShipping
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono

@Repository
interface OrderShippingRepository : ReactiveCrudRepository<OrderShipping, String> {

    fun findByOrderId(orderId: String): Mono<OrderShipping>
}