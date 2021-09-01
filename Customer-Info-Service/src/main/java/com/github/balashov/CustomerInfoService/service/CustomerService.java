package com.github.balashov.CustomerInfoService.service;

import com.github.balashov.CustomerInfoService.domain.Customer;
import com.github.balashov.CustomerInfoService.repository.CustomerRepository;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerService {

    private CustomerRepository repository;

    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }


    public Flux<Customer> findAllCustomers() {
        return repository.findAll();
    }

    public Mono<Customer> findCustomerById(int id) {
        return repository.findById(id);
    }

    public Mono<Customer> createCustomer(Customer customer) {
        return repository.findById(customer.getId()) //hoping this will prevent us from accidentally overwritting another entry
                .log()
                .switchIfEmpty(repository.save(customer));
    }

    public Mono<Customer> deposit(int customerId, double amt) {
        return repository.findById(customerId)
                .flatMap(customer -> {

                   double currentBalance = customer.getBalance();
                   currentBalance += amt;
                   customer.setBalance(currentBalance);

                   return repository.save(customer); //updates
                }).log();
    }

    public Mono<Customer> withdraw(int customerId, double amt) {
        return repository.findById(customerId)
                .flatMap(customer -> {
                    double currentBalance = customer.getBalance();
                    currentBalance -= amt;
                    currentBalance = Math.max(0, currentBalance);
                    customer.setBalance(currentBalance);

                    return repository.save(customer); //updates
                }).log();
    }
}
