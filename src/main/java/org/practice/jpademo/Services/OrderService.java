package org.practice.jpademo.Services;

import org.practice.jpademo.DTOs.OrderDTO;
import org.practice.jpademo.Model.Order;
import org.practice.jpademo.Model.OrderUpdate;
import org.practice.jpademo.Repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    public OrderRepository orderRepository;

    public void createProduct(Order order) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderId(order.getOrderId());
        orderDTO.setProductName(order.getProductName());
        orderDTO.setCustomerName(order.getCustomerName());
        orderDTO.setCustomerMobileNumber(order.getCustomerMobileNumber());
        orderRepository.save(orderDTO);

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
//
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

    public OrderDTO deleteByOrderId(String orderId) {
        OrderDTO orderDTO = orderRepository.findByOrderId(orderId);
        orderRepository.delete(orderDTO);
        return orderDTO;
    }

    public OrderDTO updateOrder(String orderId, OrderUpdate orderUpdate) {
        OrderDTO orderDTO = orderRepository.findByOrderId(orderId);

       // orderDTO.setOrderId(orderUpdate.getOrderId());
        orderDTO.setProductName(orderUpdate.getProductName());
        orderDTO.setCustomerName(orderUpdate.getCustomerName());
        orderDTO.setCustomerMobileNumber(orderUpdate.getCustomerMobileNumber());
        return orderRepository.save(orderDTO);
    }


}
