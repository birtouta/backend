package com.birtouta.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.birtouta.entities.Delivery;

@Repository
public interface DeliveryRepository  extends CrudRepository<Delivery, Long>{

}
