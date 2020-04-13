package com.birtouta.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.birtouta.entities.Store;

@Repository
public interface StoreRepository extends CrudRepository<Store, Long> {

	@Query("from Store where id = :id")
	public Store findStoreById(@Param("id") Long id);
	
	
	public List<Store> findAll(); 
}
