package org.example.orders.DTO;


import lombok.Data;

@Data
public class ProducttDTO {

    private Long id;
    private String name;
    private String description;
    private Double price;
    private Integer stock;
}
