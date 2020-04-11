package com.birtouta.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.birtouta.entities.Order;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long>{

}
