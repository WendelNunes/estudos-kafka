package com.wendelnunes.shipping.web.api.v1

import com.wendelnunes.shipping.service.OrderShippingService
import com.wendelnunes.shipping.web.api.v1.mapper.OrderShippingMapper
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/orders/shippings")
class OrderShippingController(
    private val orderShippingService: OrderShippingService
) {
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun getById(
        @PathVariable("id") id: String
    ) = orderShippingService
        .findById(id)
        .map(OrderShippingMapper::toDTO)

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun getAll() = orderShippingService
        .findAll()
        .map(OrderShippingMapper::toDTO)

    @PutMapping("/{id}/sent")
    @ResponseStatus(HttpStatus.OK)
    fun markAsSent(
        @PathVariable("id") id: String
    ) = orderShippingService
        .markAsSent(id)
        .map(OrderShippingMapper::toDTO)
}