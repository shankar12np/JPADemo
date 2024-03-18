package org.practice.jpademo.Repository;

import org.practice.jpademo.DTOs.OrderDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderDTO,Long> {
    OrderDTO findAllByOrderId(String orderId);

   List <OrderDTO> findAllByCustomerMobileNumber(String custumerMobileNumber);

   OrderDTO findByProductName(String productName);


}
