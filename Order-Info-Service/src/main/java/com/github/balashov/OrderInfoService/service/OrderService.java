package com.github.balashov.OrderInfoService.service;

import com.github.balashov.OrderInfoService.domain.Order;
import com.github.balashov.OrderInfoService.repository.OrderRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class OrderService {

    private OrderRepository repository;

    public OrderService(OrderRepository repository) {
        this.repository = repository;
    }

    public Flux<Order> findAllOrders() {
        return repository.findAll();
    }

    public Mono<Order> findOrderById(int id) {
        return repository.findById(id);
    }

    public Flux<Order> findOrdersByCustomerId(int customerId) {
        return repository.findOrdersByCustomerId(customerId);
    }

    public Mono<Order> saveOrder(Order order) {
        return repository.save(order);
    }

    public Mono<Void> deleteOrderById(int orderId) {
        return repository.deleteById(orderId);
    }


}
