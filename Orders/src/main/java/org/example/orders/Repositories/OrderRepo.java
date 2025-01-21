package org.example.orders.Repositories;

import org.example.orders.Entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;



public interface OrderRepo extends JpaRepository<Order, Long> {
}
