package com.birtouta.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.birtouta.entities.User;


// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface UserRepository extends CrudRepository<User,Long> {

	
	@Query("from User where phone = :phone")
	public User searchByPhone(@Param("phone") String phone);
}
