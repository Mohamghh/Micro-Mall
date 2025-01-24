package org.example.orders.DTO;


import jakarta.persistence.Lob;
import lombok.Data;

@Data
public class ProducttDTO {

    private Long id;
    private String name;
    private String description;
    private Double price;
    private Integer stock;
    private String imageName;
    private String imageType;

    @Lob
    private byte[] image;
}
