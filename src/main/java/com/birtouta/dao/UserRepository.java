package com.birtouta.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.birtouta.entities.User;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User,Long> {

	
//	@Query("from User where phone = :phone")
	
	public User findByPhone(@Param("phone") String phone);
	
	public User findByFirstName(@Param("first_name") String first_name);

	public List<User> findAll();
	
}
