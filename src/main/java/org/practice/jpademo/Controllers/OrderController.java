package org.practice.jpademo.Controllers;


import org.practice.jpademo.DTOs.OrderDTO;
import org.practice.jpademo.Model.Order;
import org.practice.jpademo.Services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    public OrderService orderService;

    @PostMapping("/create")
    public ResponseEntity<?> createOrder(@RequestBody Order order) {
        orderService.createProduct(order);
        return new ResponseEntity<>("Order Created", HttpStatus.CREATED);
    }

    @GetMapping("/get-order/{orderId}")
    public ResponseEntity<Order> getOrderByOrderId(@PathVariable String orderId) {
        Order order = orderService.findByOrderById(orderId);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @GetMapping("/get-order-by-product/{productName}")
    public ResponseEntity<Order> findByProductName(@PathVariable String productName) {
        Order order = orderService.findAllByProductName(productName);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @GetMapping("/get-all-orders")
    public ResponseEntity<List<OrderDTO>> findAll() {
        List<OrderDTO> data = orderService.findAll();
        return ResponseEntity.ok(data);
    }


}