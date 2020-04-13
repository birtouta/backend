package com.birtouta.services;

import com.birtouta.dao.OrderRepository;
import com.birtouta.dto.OrderDTO;
import com.birtouta.entities.Order;
import com.birtouta.mapper.ObjectMapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public Order saveOrUpdateOrder(OrderDTO orderDTO){
        Order order= ObjectMapperUtils.map(orderDTO,Order.class);
        return orderRepository.save(order);
    }

    public Order findById(Long id){
        return orderRepository.getById(id);
    }

    public List<Order> getAllOrders(){
        return orderRepository.findAll();
    }
}
