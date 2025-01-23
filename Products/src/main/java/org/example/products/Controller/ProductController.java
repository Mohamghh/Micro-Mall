package org.example.products.Controller;

import org.example.products.Entities.Productt;
import org.example.products.Service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;

import java.util.List;

@RestController
@RequestMapping("/products")
@CrossOrigin(origins = "http://localhost:3000")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // Récupérer tous les produits
    @GetMapping
    public List<Productt> getAllProducts() {
        return productService.getAllProducts();
    }

    // Récupérer un produit par ID
    @GetMapping("/{id}")
    public ResponseEntity<Productt> getProductById(@PathVariable Long id) {
        Productt product = productService.getProductById(id);
        if (product != null) {
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Ajouter un produit avec l'image
    @PostMapping(value = "/addproduct", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Productt> createProduct(@RequestPart("product") String productJson,
                                                  @RequestPart("file") MultipartFile imageFile) {
        try {
            // Convertir le JSON du produit en objet Productt
            ObjectMapper objectMapper = new ObjectMapper();
            Productt product = objectMapper.readValue(productJson, Productt.class);

            // Gérer l'image
            String imageName = imageFile.getOriginalFilename();
            String imageType = imageFile.getContentType();
            byte[] imageBytes = imageFile.getBytes();

            // Ajouter l'image à l'entité Productt
            product.setImageName(imageName);
            product.setImageType(imageType);
            product.setImage(imageBytes);

            // Sauvegarder le produit avec l'image
            Productt savedProduct = productService.saveProduct(product);

            return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Supprimer un produit
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
