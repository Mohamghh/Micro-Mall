package org.example.orders.Entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Entity
@Data
@AllArgsConstructor @NoArgsConstructor
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId; // Not a relation, just an ID
    private Long productId; // Not a relation, just an ID

    private Integer quantity;
    private Double totalPrice;
    private LocalDateTime orderDate;
}
