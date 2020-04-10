package com.birtouta.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.birtouta.entities.StoreWorker;

@Repository
public interface StoreWorkerRepository extends CrudRepository<StoreWorker, Long>{

}
