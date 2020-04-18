package com.birtouta.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.birtouta.dao.ConfigurationRepository;
import com.birtouta.dao.SessionRepository;
import com.birtouta.entities.Configuration;
import com.birtouta.entities.Session;
import com.birtouta.entities.User;
import com.birtouta.services.Metier;
import com.birtouta.services.Response;

@RestController
@RequestMapping(path="/config",consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
public class ConfigRestController {
	@Autowired
	ConfigurationRepository configurationRepository; 
	
	@Autowired
	SessionRepository sessionRepository ;
	
	@PostMapping(path="/add")
	public ResponseEntity<?> addConfig (Configuration configuration){
		return new ResponseEntity<Response>(new Response("Configuration successfully registered !", configurationRepository.save(configuration),  200, true), HttpStatus.OK);
	}
	
	@PostMapping(path="/update")
	public ResponseEntity<?> updateConfig (Configuration configuration, @RequestHeader("Token") String token){
		Session session = sessionRepository.findByToken(token);
		if(session == null) {
			return new ResponseEntity<Response>(new Response("Unauthorized Token", null,  401, false), HttpStatus.UNAUTHORIZED);
		}else {
			
			Configuration  c = session.getUser().getConfiguration(); 
			configuration.setId(c.getId());
			configuration.setUpdatedAt(Metier.getCurrentTimestamp());
			return new ResponseEntity<Response>(new Response("Configuration successfully updated !", configurationRepository.save(configuration),  200, true), HttpStatus.OK);
		}
	}
	
	@PostMapping(path="/get", consumes = MediaType.ALL_VALUE)
	public ResponseEntity<?> getConfig (@RequestHeader("Token") String token){
		Session session = sessionRepository.findByToken(token);
		if(session == null) {
			return new ResponseEntity<Response>(new Response("Unauthorized Token", null,  401, false), HttpStatus.UNAUTHORIZED);
		}else {
			
			User user = session.getUser() ;
			Configuration  configuration = user.getConfiguration(); 
			return new ResponseEntity<Response>(new Response("Configuration successfully retreived !", configuration,  200, true), HttpStatus.OK);
		}
	}
	
}
