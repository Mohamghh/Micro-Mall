package org.example.orders.Openfeign;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.example.orders.DTO.ProducttDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "PRODUCTS")
@JsonIgnoreProperties(ignoreUnknown = true)
public interface ProductFeignClient {


    @GetMapping("/products/{id}")
    ProducttDTO getProductById(@PathVariable Long id);

    @GetMapping("/products")
    PagedModel<ProducttDTO> getAllProducts();

}
