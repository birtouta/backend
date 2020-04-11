package com.birtouta.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.birtouta.entities.Configuration;

@Repository
public interface ConfigurationRepository extends CrudRepository<Configuration, Long>{

}
