package com.birtouta.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.birtouta.dao.SessionRepository;
import com.birtouta.dao.StoreRepository;
import com.birtouta.entities.Session;
import com.birtouta.entities.Store;
import com.birtouta.services.Response;

@RestController
@RequestMapping(path="/store",consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE) 
public class StoreRestController {
	
	@Autowired
	private SessionRepository sessionRepository; 
	
	@Autowired
	private StoreRepository storeRepository; 
	
	
	@PostMapping(path="/all" ,consumes = {MediaType.ALL_VALUE})
	public ResponseEntity<?> getAllStore( @RequestHeader("Token") String token){
		Session session = sessionRepository.findByToken(token);
		if(session == null) {
			return new ResponseEntity<Response>(new Response("Unauthorized Token", null,  401, false), HttpStatus.UNAUTHORIZED);

		}else {
			List<Store> stores = storeRepository.findAll();
			stores.forEach(store ->{
				store.setPhoto("https://store.houmti.net/assets/img/"+store.getPhoto());
			});	
			return new ResponseEntity<Response>(new Response("Stores successfully retreived !",stores,  200, true), HttpStatus.OK);
		}
	}
}
