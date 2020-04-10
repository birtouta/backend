package com.birtouta.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.birtouta.entities.OrderProduct;

@Repository
public interface OrderProductRepository extends CrudRepository<OrderProduct, Long>{

}
