package org.example.orders.Service;

import org.example.orders.DTO.ProducttDTO;
import org.example.orders.Entities.Order;
import org.example.orders.Openfeign.ProductFeignClient;
import org.example.orders.Repositories.OrderRepo;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepo orderRepository;
    private final ProductFeignClient productFeignClient;

    public OrderServiceImpl(OrderRepo orderRepository, ProductFeignClient productFeignClient) {
        this.orderRepository = orderRepository;
        this.productFeignClient = productFeignClient;
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    @Override
    public Order saveOrder(Order order) {
        // Récupérer les informations du produit via ProductFeignClient
        ProducttDTO product = productFeignClient.getProductById(order.getProductId());

        // Vérifier si le produit existe
        if (product == null) {
            throw new RuntimeException("Produit introuvable pour l'ID : " + order.getProductId());
        }

        // Vérifier si le stock est suffisant
        if (product.getStock() < order.getQuantity()) {
            throw new RuntimeException("Stock insuffisant pour le produit : " + product.getName());
        }

        // Calculer le prix total
        double totalPrice = product.getPrice() * order.getQuantity();
        order.setTotalPrice(totalPrice);

        // Définir la date de commande
        order.setOrderDate(LocalDateTime.now());

        // Ajouter les informations du produit à la commande
        order.setProduct(product);  // Ajouter l'objet ProducttDTO à la commande

        // Enregistrer la commande
        return orderRepository.save(order);
    }


    @Override
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
}
