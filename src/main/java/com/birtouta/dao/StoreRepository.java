package com.birtouta.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.birtouta.entities.Store;

@Repository
public interface StoreRepository extends CrudRepository<Store, Long> {

	
	
}
