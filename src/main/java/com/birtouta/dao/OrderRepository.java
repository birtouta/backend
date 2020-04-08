package com.birtouta.dao;

import org.springframework.data.repository.CrudRepository;

import com.birtouta.entities.Order;


public interface OrderRepository extends CrudRepository<Order, Long>{

}
