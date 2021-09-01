package com.github.balashov.OrderInfoService.controller;

import com.github.balashov.OrderInfoService.domain.Order;
import com.github.balashov.OrderInfoService.service.OrderService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @GetMapping("")
    public Flux<Order> findAllOrders() {
        return service.findAllOrders();
    }

    @PostMapping("")
    public Mono<Order> saveOrder(Order order) {
        return service.saveOrder(order);
    }

    @GetMapping("/{orderId}")
    public Mono<Order> findOrderById(int orderId) {
        return service.findOrderById(orderId);
    }

    @DeleteMapping("")
    public Mono<Void> deleteOrderById(int orderId) {
        return service.deleteOrderById(orderId);
    }

    @GetMapping("/customer/{customerId}")
    public Flux<Order> findOrdersByCustomerId(int customerId) {
        return service.findOrdersByCustomerId(customerId);
    }


}
