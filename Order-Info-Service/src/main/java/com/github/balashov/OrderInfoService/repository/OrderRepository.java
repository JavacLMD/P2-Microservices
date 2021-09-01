package com.github.balashov.OrderInfoService.repository;

import com.github.balashov.OrderInfoService.domain.Order;
import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface OrderRepository extends ReactiveCassandraRepository<Order, Integer> {


    @AllowFiltering
    @Query("SELECT * FROM scifi.orders WHERE customer_id = ?0 ALLOW FILTERING")
    Flux<Order> findOrdersByCustomerId(int customerId);

}
