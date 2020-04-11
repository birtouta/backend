package com.birtouta.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.birtouta.entities.User;

@Repository
public interface UserRepository extends CrudRepository<User,Long> {

	
//	@Query("from User where phone = :phone")
	
	public User findByPhone(@Param("phone") String phone);
	
	public User findByFirstName(@Param("first_name") String first_name);
	
}
