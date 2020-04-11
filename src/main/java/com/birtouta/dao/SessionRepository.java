package com.birtouta.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.birtouta.entities.Session;

@Repository
public interface SessionRepository extends CrudRepository<Session, Long> {
	
	public Session findByToken(@Param("token") String token);
}
