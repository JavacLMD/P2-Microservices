package com.github.balashov.OrderInfoService.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Table("orders")
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @PrimaryKeyColumn(value = "order_id", type = PrimaryKeyType.PARTITIONED)
    @Getter
    @Setter
    private int orderId;

    @PrimaryKeyColumn(value = "customer_id", type = PrimaryKeyType.CLUSTERED)
    @Getter
    @Setter
    private int customerId;

    @Column
    @Getter
    @Setter
    Map<Integer, Integer> items = new HashMap<>(); //key (item id) value (quantity)

    @Column
    @Getter
    @Setter
    double total = 0;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return orderId == order.orderId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId);
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", customerId=" + customerId +
                ", items=" + items +
                ", total=" + total +
                '}';
    }
}
