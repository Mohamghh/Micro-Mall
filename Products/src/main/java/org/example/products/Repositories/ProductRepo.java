package org.example.products.Repositories;


import org.example.products.Entities.Productt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<Productt, Long> {
}
