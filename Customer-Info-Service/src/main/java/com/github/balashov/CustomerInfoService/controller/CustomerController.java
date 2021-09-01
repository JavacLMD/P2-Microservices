package com.github.balashov.CustomerInfoService.controller;

import com.github.balashov.CustomerInfoService.domain.Customer;
import com.github.balashov.CustomerInfoService.service.CustomerService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("")
    public Flux<Customer> findAllCustomers() {
        return customerService.findAllCustomers().log();
    }

    @GetMapping("/{customerId}")
    public Mono<Customer> findCustomerById(@PathVariable int customerId) {
        return customerService.findCustomerById(customerId).log();
    }

    @PostMapping("")
    public Mono<Customer> createCustomer(@RequestBody Customer customer) {
        return customerService.createCustomer(customer);
    }

    @PutMapping("/{customerId}/deposit/{amount}")
    public Mono<Customer> deposit(@PathVariable int customerId, @PathVariable double amount) {
        return customerService.deposit(customerId, amount);
    }

    @PutMapping("/{customerId}/withdraw/{amount}")
    public Mono<Customer> withdraw(@PathVariable int customerId, @PathVariable double amount) {
        return customerService.withdraw(customerId, amount).log();
    }

}
