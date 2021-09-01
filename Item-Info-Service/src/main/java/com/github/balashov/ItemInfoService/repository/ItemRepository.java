package com.github.balashov.ItemInfoService.repository;

import com.github.balashov.ItemInfoService.domain.Item;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface ItemRepository extends ReactiveCassandraRepository<Item, Integer> {

    @Query(value = "SELECT * FROM scifi.items WHERE category CONTAINS ?0 ALLOW FILTERING", allowFiltering = true)
    Flux<Item> findByCategory(String category);

    @Query(value = "SELECT * FROM scifi.items WHERE name = ?0 ALLOW FILTERING", allowFiltering = true)
    Flux<Item> findByName(String name);

}
