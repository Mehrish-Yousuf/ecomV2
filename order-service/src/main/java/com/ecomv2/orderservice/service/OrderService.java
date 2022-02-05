package com.ecomv2.orderservice.service;


import com.ecomv2.orderservice.DTO.OrderDTO;
import com.ecomv2.orderservice.domain.Order;

public interface OrderService {
    public Order saveOrder(Order order);

    OrderDTO getOrderDetails(Long orderId);
}
