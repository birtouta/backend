package com.birtouta.dao;

import org.springframework.data.repository.CrudRepository;

import com.birtouta.entities.OrderProduct;

public interface OrderProductRepository extends CrudRepository<OrderProduct, Long>{

}
