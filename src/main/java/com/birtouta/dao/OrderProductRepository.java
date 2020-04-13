package com.birtouta.dao;

import com.birtouta.entities.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderProductRepository extends JpaRepository<OrderProduct, Long> {
    OrderProduct getById(Long id);
    List<OrderProduct> findAllProductsByOrder(Long id_order);
}
