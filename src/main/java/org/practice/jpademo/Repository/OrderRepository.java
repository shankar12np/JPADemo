package org.practice.jpademo.Repository;

import org.practice.jpademo.DTOs.OrderDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<OrderDTO, Long> {
    OrderDTO findAllByOrderId(String orderId);


    OrderDTO findByProductName(String productName);

    OrderDTO deleteByOrderId(String orderId);


    OrderDTO findByOrderId(String orderId);




}
