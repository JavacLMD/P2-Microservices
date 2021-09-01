package com.github.balashov.CustomerInfoService.repository;

import com.github.balashov.CustomerInfoService.domain.Customer;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends ReactiveCassandraRepository<Customer, Integer> {



}
