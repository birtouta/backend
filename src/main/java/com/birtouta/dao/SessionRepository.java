package com.birtouta.dao;

import org.springframework.data.repository.CrudRepository;

import com.birtouta.entities.Session;


public interface SessionRepository extends CrudRepository<Session, Long> {

}
