package org.example.orders.Controller;


import org.example.orders.Entities.Order;
import org.example.orders.Openfeign.ProductFeignClient;
import org.example.orders.Repositories.OrderRepo;
import org.example.orders.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@CrossOrigin(origins = "http://localhost:3000")
public class OrderController {

    private final OrderService orderService;


    @Autowired
    private final ProductFeignClient productFeignClient;

    @Autowired
    private OrderRepo orderRepo;

    public OrderController(OrderService orderService, ProductFeignClient productFeignClient) {
        this.orderService = orderService;
        this.productFeignClient = productFeignClient;
    }

    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        Order order = orderService.getOrderById(id);
        order.setProduct(productFeignClient.getProductById(order.getProductId()));


        return ResponseEntity.ok(order);


    }


    @PostMapping("/addorder")
    public Order createOrder(@RequestBody Order order) {
        return orderService.saveOrder(order);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }


}
