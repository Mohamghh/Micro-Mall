package org.example.products.Service;

import org.example.products.Entities.Productt;

import java.util.List;

public interface ProductService {

    List<Productt> getAllProducts();
    Productt getProductById(Long id);
    Productt saveProduct(Productt product);
    void deleteProduct(Long id);
}
