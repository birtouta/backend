package com.birtouta.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.birtouta.dao.SessionRepository;
import com.birtouta.dao.StoreRepository;
import com.birtouta.entities.Session;
import com.birtouta.entities.Store;

@RestController
@RequestMapping(path="/store") 
public class StoreRestController {
	
	@Autowired
	private SessionRepository sessionRepository; 
	
	@Autowired
	private StoreRepository storeRepository; 
	
	
	@GetMapping(path="/all")
	public ResponseEntity<?> getAllStore( @RequestHeader("token") String token){
		Session session = sessionRepository.findByToken(token);
		if(session == null) {
			return new ResponseEntity<String>("Unauthorized Token", HttpStatus.UNAUTHORIZED);
		}else {
			return new ResponseEntity<List<Store>>(storeRepository.findAll(), HttpStatus.OK);
		}
	}
}
