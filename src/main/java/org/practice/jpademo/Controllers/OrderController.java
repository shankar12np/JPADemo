package org.practice.jpademo.Controllers;


import org.practice.jpademo.DTOs.OrderDTO;
import org.practice.jpademo.Model.Order;
import org.practice.jpademo.Model.OrderUpdate;
import org.practice.jpademo.Model.PlaceOrder;
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

    @PostMapping("/place-order")
    public ResponseEntity<?> placeOrder(@RequestBody PlaceOrder placeOrder){
        Order order = orderService.placeOrder(placeOrder);
        return new ResponseEntity<>("Ordered Placed ! ",HttpStatus.OK);
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

    @GetMapping("/list-orders-by-pagination")
    public ResponseEntity<?> listOrdersByPagination(@RequestParam Integer pageNumber,@RequestParam Integer pageSize,
                                                    @RequestParam String sortBy, @RequestParam String sortOrder){
        List<Order> orderList = orderService.listOrderByPageNumber(pageNumber,pageSize,sortBy,sortOrder);
        return new ResponseEntity<>(orderList,HttpStatus.OK);
    }

    @DeleteMapping("/delete/{orderId}")
    public ResponseEntity<?> deleteOrder(@PathVariable String orderId) {
        OrderDTO data = orderService.deleteByOrderId(orderId);
        return new ResponseEntity<>("Order Deleted", HttpStatus.OK);
    }
    @PutMapping("/update/{orderId}")
    public ResponseEntity<?> updateOrder(@PathVariable String orderId, @RequestBody OrderUpdate orderUpdate) {
        orderService.updateOrder(orderId,orderUpdate);
        return ResponseEntity.ok("Order Updated");
    }



}
