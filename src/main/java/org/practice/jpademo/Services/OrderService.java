package org.practice.jpademo.Services;

import org.aspectj.weaver.ast.Or;
import org.practice.jpademo.DTOs.OrderDTO;
import org.practice.jpademo.Model.Order;
import org.practice.jpademo.Model.OrderUpdate;
import org.practice.jpademo.Model.PlaceOrder;
import org.practice.jpademo.Repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class OrderService {

    @Autowired
    public OrderRepository orderRepository;

    public void createProduct(Order order) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderId(order.getOrderId());

        orderDTO.setCustomerMobileNumber(order.getCustomerMobileNumber());
        orderRepository.save(orderDTO);

    }

    public Order placeOrder(PlaceOrder placeOrder) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderId(UUID.randomUUID().toString());
        orderDTO.setProductName(placeOrder.getProductName());
        orderDTO.setCustomerName(placeOrder.getCustomerName());
        orderDTO.setCustomerMobileNumber(placeOrder.getCustomerMobileNumber());
        //saving
        orderDTO = orderRepository.save(orderDTO);
// Create and return an Order object with the properties from the saved orderDTO
        Order order = new Order();
        order.setOrderId(orderDTO.getOrderId());
        order.setProductName(orderDTO.getProductName());
        order.setCustomerName(orderDTO.getCustomerName());
        order.setCustomerMobileNumber(orderDTO.getCustomerMobileNumber());
        return order;
    }

    public Order findByOrderById(String orderId) {
        OrderDTO orderDTO = orderRepository.findAllByOrderId(orderId);

        Order order = new Order();
        order.setOrderId(orderDTO.getOrderId());
        order.setProductName(orderDTO.getProductName());
        order.setCustomerName(orderDTO.getCustomerName());
        order.setCustomerMobileNumber(orderDTO.getCustomerMobileNumber());
        return order;

    }

    public Order findAllByProductName(String productName) {
        OrderDTO orderDTO = orderRepository.findByProductName(productName);

        Order order = new Order();

        order.setOrderId(orderDTO.getOrderId());
        order.setProductName(orderDTO.getProductName());
        order.setCustomerName(orderDTO.getCustomerName());
        order.setCustomerMobileNumber(orderDTO.getCustomerMobileNumber());
        return order;
    }

    public List<OrderDTO> findAll() {
        return orderRepository.findAll();
    }


//    public List<Order> findAllOrders() {
//        List<OrderDTO> data = orderRepository.findAll();
//        List<Order> orders = new ArrayList<>();
//        for (OrderDTO orderDTO : data) {
//            Order order = new Order();
//            order.setOrderId(orderDTO.getOrderId());
//            order.setCustomerName(orderDTO.getCustomerName());
//            order.setCustomerMobileNumber(orderDTO.getCustomerMobileNumber());
//            order.setProductName(orderDTO.getProductName());
//
//        }
//
//        return orders;
//    }

    public List<Order> listOrderByPageNumber(int pageNumber, int pageSize, String sortBy, String sortOrder) {
        Sort sort;
        if (sortOrder.equalsIgnoreCase("DESC")) {
            sort = Sort.by(sortBy).descending();
        } else {
            sort = Sort.by(sortBy).ascending();
        }

        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sort);
        Page<OrderDTO> pageResponse = orderRepository.findAll(pageRequest);

        List<Order> orders = new ArrayList<>();
        for (
                OrderDTO orderDTO : pageResponse.getContent()) {
            Order order = new Order();
            order.setOrderId(orderDTO.getOrderId());
            order.setProductName(orderDTO.getProductName());
            order.setCustomerName(orderDTO.getCustomerName());
            order.setCustomerMobileNumber(orderDTO.getCustomerMobileNumber());
            orders.add(order);
        }
        return orders;

    }

    public OrderDTO deleteByOrderId(String orderId) {
        OrderDTO orderDTO = orderRepository.findByOrderId(orderId);
        orderRepository.delete(orderDTO);
        return orderDTO;
    }

    public void updateOrder(String orderId, OrderUpdate orderUpdate) {
        OrderDTO orderDTO = orderRepository.findByOrderId(orderId);
        if (orderDTO != null) {
            // Update the properties of the OrderDTO with the values from the OrderUpdate object
            orderDTO.setProductName(orderUpdate.getProductName());
            orderDTO.setCustomerName(orderUpdate.getCustomerName());
            orderDTO.setCustomerMobileNumber(orderUpdate.getCustomerMobileNumber());

            orderRepository.save(orderDTO);

        } else {
            // Handle the case where the order with the given orderId is not found
            throw new RuntimeException("Order not found for orderId: " + orderId);
        }
    }

}
