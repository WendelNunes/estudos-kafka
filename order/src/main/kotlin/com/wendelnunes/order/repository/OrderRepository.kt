package com.wendelnunes.order.repository

import com.wendelnunes.order.domain.Order
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface OrderRepository : ReactiveCrudRepository<Order, String>