package com.birtouta.services;

import com.birtouta.dao.OrderProductRepository;
import com.birtouta.dao.OrderRepository;
import com.birtouta.entities.Order;
import com.birtouta.entities.OrderProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderProductService {
    @Autowired
    private OrderProductRepository orderProductRepository;

    @Autowired
    private OrderRepository orderRepository;

    public OrderProduct saveOrUpdateOrderProduct(OrderProduct orderProduct, Long id_order) {
        Order order = orderRepository.getById(id_order);
        if (order != null) {
        	orderProduct.setOrder(order);
        	return orderProductRepository.save(orderProduct);
         }
        return null; 
    }

    public void saveAllOrderProducts(Order order) {
        Order o = orderRepository.getById(order.getId());
        if (o != null) {
            order.getOrderProducts().forEach(orderProduct ->
                    {
                        orderProduct.setOrder(o);
                        orderProductRepository.save(orderProduct);
                    }
            );
        }
    }

    public List<OrderProduct> getAllProductsByOrder(Long id_order) {
        Order o = orderRepository.getById(id_order);
        return orderProductRepository.findAllProductsByOrder(id_order);
    }

    public OrderProduct findById(Long id) {
        return orderProductRepository.getById(id);
    }

    public void delete(Long id) {
        OrderProduct orderProduct = findById(id);
        orderProductRepository.delete(orderProduct);
    }

    public List<OrderProduct> getAllOrderProducts() {
        return orderProductRepository.findAll();
    }
}
