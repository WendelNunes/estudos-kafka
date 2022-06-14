package com.wendelnunes.order.web.api.v1

import com.wendelnunes.order.service.OrderService
import com.wendelnunes.order.web.api.v1.dto.OrderDTO
import com.wendelnunes.order.web.api.v1.mapper.OrderMapper
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/v1/orders")
class OrderController(
    private val service: OrderService,
) {
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(
        @RequestBody orderDTO: OrderDTO
    ): Mono<OrderDTO> {
        return this.service
            .create(OrderMapper.toEntity(orderDTO))
            .map(OrderMapper::toDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun delete(
        @PathVariable("id") id: String
    ) = this.service
        .deleteById(id)

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun getById(
        @PathVariable("id") id: String
    ) = this.service
        .findById(id)
        .map(OrderMapper::toDTO)

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun getAll() = this.service
        .findAll()
        .map(OrderMapper::toDTO)
}