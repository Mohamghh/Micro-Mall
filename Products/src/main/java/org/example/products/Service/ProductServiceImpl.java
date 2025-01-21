package org.example.products.Service;

import org.example.products.Entities.Productt;
import org.example.products.Repositories.ProductRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepo productRepository;

    public ProductServiceImpl(ProductRepo productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Productt> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Productt getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public Productt saveProduct(Productt product) {
        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
